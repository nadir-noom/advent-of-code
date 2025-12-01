# Advent of Code
A repository for my solutions to the annual "Advent of Code" (AoC) event - see https://adventofcode.com/.

In previous years, my solutions have been programmed in Clojure and use Boot to run.
This year (2025), I continue to use Babashka (still Clojure!) to implement my solutions; there is less overhead than a standard boot project and fits my free time better.

Generally, any solution I write to gain a star will be self-contained.
Doing this makes it easier to share the solution for a single star without spoiling the answer.

# Running

## < 2022
To use a solution, you will need to change directory into the day/star (e.g. cd day-1/star-1).

In order to run the tests:
```
boot test
```

In order to run the code to print out the solution for the input data:
```
boot run
```

A description of the exercise for the day/star in included in each project.

## 2022
You will need to have [Basbahka](https://github.com/babashka/babashka) installed.
Once installed, you can run scripts by doing:
```
cd day-X

./star-1.clj
./star-2.clj
```
