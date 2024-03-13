use std::cmp::max;

pub fn compute_lcs(
    seq1: &str,
    seq2: &str
) -> String {
    
    let m: usize = seq1.len() ; 
    let n: usize = seq2.len() ;  

    let mut L: Vec<Vec<u8>> = vec![ vec![ 0 ; n + 1 ] ; m + 1 ] ; 
    for i in 0..(m+1) {
        for j in 0..(n+1) {
            if i == 0 || j == 0 {
                L[i][j] = 0 ; 
            }
            else if seq1.chars().nth( i-1 ).unwrap() == seq2.chars().nth( j - 1 ).unwrap() {
                L[i][j] = L[i-1][j-1] + 1 ;
            }
            else {
                L[i][j] = max( L[i][j-1] , L[i-1][j] ) ; 
            }
        }
    }

    let mut index = L[m][n] as usize; 
    let mut lcs: Vec<char> = vec![ '\0' ; index + 1 ]; 

    let mut i = m ; 
    let mut j = n ; 
    while i > 0 && j > 0 {
        if seq1.chars().nth( i - 1 ).unwrap() == seq2.chars().nth( j - 1 ).unwrap() {
            lcs[ index - 1 ] = seq1.chars().nth( i - 1 ).unwrap() ; 
            i -= 1 ;
            j -= 1 ; 
            index = index - 1 ;
        }
        else if L[i-1][j] > L[i][j-1] {
            i -= 1 ;
        }
        else {
            j -= 1 ;
        }
    }

    lcs.into_iter().collect()
}