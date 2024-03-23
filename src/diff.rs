use std::cmp::max;

pub enum DiffType {
    Add,
    Del,
    None,
}

pub struct LineDiff<'a> {
    pub line: &'a str,
    pub diff_type: DiffType,
}

pub fn diff_str<'a>(seq1: &[&'a str], seq2: &[&'a str]) -> (Vec<LineDiff<'a>>,usize,usize,usize) {
    let m: usize = seq1.len();
    let n: usize = seq2.len();

    let mut lcs_lengths: Vec<Vec<u8>> = vec![vec![0; n + 1]; m + 1];
    for i in 0..=m {
        for j in 0..=n {
            if i == 0 || j == 0 {
                // LCS( ε , ε ) = ε and len( ε ) = 0
                lcs_lengths[i][j] = 0;
            } else if seq1[i - 1] == seq2[j - 1] {
                // Let seq1[i-1] = seq2[j-1] = a
                // LCS( seq1_i , seq2_j ) = LCS( seq1_{i-1} , seq2_{j-1} ) ^ a
                lcs_lengths[i][j] = lcs_lengths[i - 1][j - 1] + 1;
            } else {
                lcs_lengths[i][j] = max(lcs_lengths[i][j - 1], lcs_lengths[i - 1][j]);
            }
        }
    }
    let mut diffs: Vec<LineDiff> = Vec::new();
    let mut stack: Vec<(usize, usize)> = Vec::new();
    let mut add_count: usize = 0 ; 
    let mut del_count: usize = 0 ; 
    let mut none_count: usize = 0 ;
    stack.push((m, n));
    while !stack.is_empty() {
        let (i, j) = stack.pop().unwrap_or((0, 0));
        if i == 0 || j == 0 {
            break;
        }
        if seq1[i - 1] == seq2[j - 1] {
            stack.push((i - 1, j - 1));
            diffs.push(LineDiff {
                line: seq1[i - 1],
                diff_type: DiffType::None,
            });
            none_count += 1 ; 
        } else if lcs_lengths[i - 1][j] > lcs_lengths[i][j - 1] {
            stack.push((i - 1, j));
            diffs.push(LineDiff {
                line: seq1[i - 1],
                diff_type: DiffType::Del,
            });
            del_count += 1 ; 
        } else if lcs_lengths[i - 1][j] <= lcs_lengths[i][j - 1] {
            stack.push((i, j - 1));
            diffs.push(LineDiff {
                line: seq2[j - 1],
                diff_type: DiffType::Add,
            });
            add_count += 1 ;
        }
    }

    diffs.reverse();
    (diffs , add_count , del_count , none_count)
}