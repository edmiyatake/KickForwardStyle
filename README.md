# TopWordFrequency Program – Kick Forward Style

## Overview

The `Nine.java` program is a Java application written in the **Kick Forward style**, where:

- Each function receives, as its final parameter, a "next" function to call.
- The output of each function is immediately passed to this next function.
- The overall process forms a pipeline, with each function forwarding its result to the next one via function parameters.

The program:

- Reads a text file and extracts all words with 2 or more alphabetic characters.
- Normalizes words to lowercase.
- Filters out stop words using a comma-separated list from `stopwords.txt`.
- Computes word frequencies using a series of chained function calls.
- Sorts and prints the top 25 most frequent words.

## Kick Forward Style Constraints

- Each function takes a **"next" function** (usually the last parameter) that gets called with the current function’s result.
- This enforces a **data-forwarding pipeline**, where logic is broken into discrete steps.
- Each step processes data and immediately kicks it forward to the next step.
- Emphasis is on **clarity, modularity**, and avoiding shared mutable state.

## Folder Structure

Place the following files in the same directory:

- `Nine.java` — Main program written in Kick Forward style
- `stopwords.txt` — Comma-separated list of stop words (e.g., `a,an,the,and,...`)
- `pride-and-prejudice.txt` — Input file for analysis

## How to Compile and Run

1. Open a terminal or command prompt.

2. Navigate to your project directory:

3. Compile the Java program:
   javac Nine.java

4. Run the program:
   java Nine pride-and-prejudice.txt

** For the repl.it, the text files will be in the main directory, so you have to use ../../../path to access files from the main parent directory.

Example: java Nine ../../../stopwords.txt ../../../pride-prejudice.txt

## Expected Output
---------------
Top 25 most frequent non-stop words in the input file, printed in descending order:

Example:
mr - 786
elizabeth - 635
very - 488
darcy - 418
such - 395
mrs - 343
much - 329