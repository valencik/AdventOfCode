Advent Of Code in Scala with Yak Shaving
========================================

See [Advent Of Code](http://adventofcode.com)


- [x] [Day 1: Inverse Captcha](src/main/scala/ca/valencik/DayOne.scala)
Yak Shave: [Create Giter8 template](#giter8-template)

- [x] [Day 2: Corruption Checksum](src/main/scala/ca/valencik/DayTwo.scala)
Yak Shave: [Travis CI](#travis-ci)

- [x] [Day 3: Spiral Memory](src/main/scala/ca/valencik/DayThree.scala)
Yak Shave: [Scalastyle](#scalastyle)


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
