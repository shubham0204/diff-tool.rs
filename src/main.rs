mod lcs;
use lcs::compute_lcs;
use lcs::compute_lcs_sentences;
use std::env;
use std::fs;

fn read_file( filepath: &String ) -> String {
    fs::read_to_string( filepath ).expect( "Could not read to string" )
}

fn main() {
    let args: Vec<String> = env::args().collect() ; 
    let contents1: String = read_file( &args[1] ) ;
    let contents2: String = read_file( &args[2] ) ; 
    let lines1: Vec<&str> = contents1.split( "\n" ).collect() ;
    let lines2: Vec<&str> = contents2.split( "\n" ).collect() ;

    let lcs = compute_lcs_sentences(lines1, lines2) ;
    for line in lcs {
        println!( "{}" , line ) ; 
    }
}