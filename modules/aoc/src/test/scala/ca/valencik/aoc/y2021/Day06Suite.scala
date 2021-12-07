package ca.valencik.aoc.y2021

import fs2.Stream
import munit.CatsEffectSuite

class Day06Suite extends CatsEffectSuite:
  val example = Stream("3,4,3,1,2")

  test("Day06 part one (example)") {
    assertIO(Day06.part1(example), "5934")
  }

  test("Day06 part two (example)") {
    assertIO(Day06.part2(example), "26984457539")
  }
