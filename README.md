Advent Of Code in Scala
=======================

See [Advent Of Code](http://adventofcode.com)


[![Build Status](https://travis-ci.org/valencik/AdventOfCode.svg?branch=travis)](https://travis-ci.org/valencik/AdventOfCode)
[![codecov](https://codecov.io/gh/valencik/AdventOfCode/branch/master/graph/badge.svg)](https://codecov.io/gh/valencik/AdventOfCode)


2018
====

Week One
--------

- [x] [Day 1: Chronal Calibration](src/main/scala/ca/valencik/ChronalCalibrator.scala)


2017
====

Week One
--------

- [x] [Day 1: Inverse Captcha](src/main/scala/ca/valencik/InverseCaptcha.scala)
Yak Shave: [Create Giter8 template](#giter8-template)

- [x] [Day 2: Corruption Checksum](src/main/scala/ca/valencik/CorruptionChecksum.scala)
Yak Shave: [Travis CI](#travis-ci)

- [x] [Day 3: Spiral Memory](src/main/scala/ca/valencik/SpiralMemory.scala)
Yak Shave: [Scalastyle](#scalastyle)

- [x] [Day 4: High-Entropy Passphrases](src/main/scala/ca/valencik/Passphrases.scala)
Yak Shave: [PR Template](#pr-template)

- [x] [Day 5: A Maze of Twisty Trampolines, All Alike](src/main/scala/ca/valencik/TwistyTrampolines.scala)
Yak Shave: [High Performance Mutable Arrays](#high-performance-mutable-arrays)

- [x] [Day 6: Memory Reallocation](src/main/scala/ca/valencik/MemoryReallocation.scala)
Yak Shave: [Auto format with Scalafmt](#auto-format-with-scalafmt)

- [x] [Day 7: Recursive Circus](src/main/scala/ca/valencik/RecursiveCircus.scala)
Yak Shave: [Test Coverage](#test-coverage)


Week Two
--------

- [x] [Day 8: I Heard You Like Registers](src/main/scala/ca/valencik/Registers.scala)

- [x] [Day 9: Stream Processing](src/main/scala/ca/valencik/StreamProcessing.scala)
Yak Shave: [Bloop](#bloop)

- [x] [Day 10: Knot Hash](src/main/scala/ca/valencik/KnotHash.scala)


Yak Shaves
==========

## Giter8 Template

This is really the start of the idea for extending Advent Of Code with yak shaving.
I learned about AOC (Advent Of Code), wanted to do Day 1 right away, and found myself mucking about with a Giter8 template before I wrote my first line of code.
So, it only seemed appropriate.
My first yak shave was creating a [Giter8 template](https://github.com/valencik/scala-starter.g8) to create this repo.


## Travis CI

We definitely need continuous integration and testing for AOC.
Day 2 yak shave gets this [.travis.yml](.travis.yml) setup to build and run our tests.


## Scalastyle

Scalastyle checks your scala code for common mistakes and bad practices.
I've taken the configuration used by [typelevel/cats](https://github.com/typelevel/cats) with a few custom rules removed.
The Travis CI setup got an update to make scalastyle run on CI for both `src` and `tests`.


## PR Template

I forgot to update the README when I implemented Day 3's yak shave, so I figured today's yak shave could be a simple PR template to remind me to update the README in the future.


## High Performance Mutable Arrays

Today we yak shaved in everyone's favourite activity, performance optimization.
My first implementation of the part two solution took on the order of 200 seconds for my puzzle input.
Admittedly this was a somewhat evil problem and solution pairing.
With over 28 million steps in the maze for that input we were creating as many `Lists` and function calls.
Refactoring to use `Array` instead of `List` was easy.
And a simple import of [`scala.collection.mutable.ArrayOps`](http://www.scala-lang.org/api/2.12.2/scala/collection/mutable/ArrayOps.html#update) gives access to `Array.update` which mutates the `Array` in place.
With the mutation happening in place we no longer needed to create the whole object each iteration so the recursive function got a bit of a refactor.
SBT initially reported the mutable `Array` solution took 0 seconds to run.
I had to add a performance timing util to find out it actually took on the order of 70 milliseconds!
Truly a remarkable speedup.
Fun!


## Auto format with Scalafmt

[Scalafmt](http://scalameta.org/scalafmt/) turns messy code into controversially formatted code it seems.
Scalafmt is very configurable but remains quite easy to use.
Our [scalafmt config](.scalafmt.conf) is set to align code on found in the git tracked files and to maintain a max 80 columns.
The align option appears to be very controversial, eliciting uncommonly strong reactions from the few people I showed it to.
Nevertheless, our CI now tests that the code is formatted to scalafmt's liking.


## Test Coverage

On mission critical code, like that operating on Santa's computer to print the Naughty or Nice List, one would be wise to track test coverage.
Using the [sbt-scoverage](https://github.com/scoverage/sbt-scoverage) plugin we produce coverage reports that are then sent and checked by [codecov.io](https://codecov.io/gh/valencik/AdventOfCode/).
Codecov.io is well integrated with GitHub and enables you to set coverage thresholds that are used in a PR check.


## Bloop

[Bloop](https://scalacenter.github.io/bloop/) is a new tool from the Scala Center that speeds up your edit-compile-test workflow by keeping warm JVMs available for these tasks.
Installing and setting it up is quite easy, involving a single sbt plugin and a system level package installable via Homebrew.
I can now run `bloop compile -p root` outside of an sbt session and get very fast compiles.
Additionally the following commands are also quite useful:
- `bloop test --project root`
- `bloop run --project root --main ca.valencik.StreamProcessing --args "$(pbpaste)"`
