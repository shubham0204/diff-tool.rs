# `diff` Tool In Rust

![diff_tool](https://github.com/shubham0204/diff-tool.rs/assets/41076823/d0f68749-08f6-40a9-80b8-6171a2436a68)

## Setup

To compile the tool, make sure that you have a [Rust toolchain installation](https://www.rust-lang.org/tools/install) targeted for your system. Once installed,

```
$> git clone https://github.com/shubham0204/diff-tool.rs
$> cd diff-tool.rs
$> cargo build --release
$> cp target/release/diff .
```

The `diff` tool is now available in the root directory of the project. Use text files from `samples` to test,

```
Usage: ./diff <filepath1> <filepath2>
```

```
$> ./diff samples/sample_02/script1.kt samples/sample_02/script2.kt
```

> [!IMPORTANT]  
> Most Linux distros come with a builtin `diff` tool installed. As both, the executable from this project and the pre-installed `diff` tool have the same name, use `./diff` to use the project's executable.

## Useful Resources

* [`codingchallenges.fyi`](https://codingchallenges.fyi/challenges/challenge-diff)
* [Programiz: Longest Common Subsequence](https://www.programiz.com/dsa/longest-common-subsequence)
* [Wikipedia: Longest Common Subsequence](https://en.wikipedia.org/wiki/Longest_common_subsequence)
* [Write your own diff for fun](https://alex.dzyoba.com/blog/writing-diff/)
* [Enjoy Algorithms: Longest Common Subsequence](https://www.enjoyalgorithms.com/blog/longest-common-subsequence)