mod diff;
use colored::Colorize;
use diff::{diff_str, DiffType};
use std::env;
use std::fs;
use std::time::Instant;

fn read_file(filepath: &String) -> String {
    fs::read_to_string(filepath).expect( format!( "Failed to read file at {}" , filepath ).as_str() )
}

fn main() {

    let filepath1 = env::args().nth(1).expect( "Path to first file not provided. Expected: ./diff <path1> <path2>" ) ; 
    let filepath2 = env::args().nth(2).expect( "Path to second file not provided. Expected: ./diff <path1> <path2>" ) ; 

    let contents1: String = read_file( &filepath1 );
    let contents2: String = read_file( &filepath2 );
    let lines1: Vec<&str> = contents1.lines().collect();
    let lines2: Vec<&str> = contents2.lines().collect();

    let start = Instant::now();
    let (diffs , add_count, del_count, none_count) = diff_str(&lines1, &lines2);
    for diff in diffs {
        println!(
            "{} {}",
            match diff.diff_type {
                DiffType::Add => "+",
                DiffType::Del => "-",
                DiffType::None => " ",
            },
            match diff.diff_type {
                DiffType::Add => diff.line.bright_green(),
                DiffType::Del => diff.line.bright_red(),
                DiffType::None => diff.line.bright_white(),
            }
        );
    }
    let duration = start.elapsed();
    println!( "{} lines added" , add_count ) ; 
    println!( "{} lines removed" , del_count ) ; 
    println!( "{} lines unchanged" , none_count ) ; 
    println!( "diff generated in {:?}", duration ) ;
}
