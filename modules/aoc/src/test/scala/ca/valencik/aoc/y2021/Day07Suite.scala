package ca.valencik.aoc.y2021

import fs2.Stream
import munit.CatsEffectSuite

class Day07Suite extends CatsEffectSuite:
  val example = Stream("16,1,2,0,4,2,7,1,2,14")

  test("Day07 part one (example)") {
    assertIO(Day07.part1(example), "37")
  }

  test("Day07 part two (example)") {
    assertIO(Day07.part2(example), "168")
  }
