mod lcs;
use lcs::compute_lcs;

fn main() {
    let seq1: &str = "ABCD" ; 
    let seq2: &str = "PQRSABXY" ; 
    let lcs = compute_lcs(seq1, seq2) ; 
    println!( "{}" , lcs ) ;
}