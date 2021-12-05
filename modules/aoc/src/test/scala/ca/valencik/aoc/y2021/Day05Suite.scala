package ca.valencik.aoc.y2021

import fs2.Stream
import munit.CatsEffectSuite

class Day05Suite extends CatsEffectSuite:
  val example = Stream(
    """|0,9 -> 5,9
       |8,0 -> 0,8
       |9,4 -> 3,4
       |2,2 -> 2,1
       |7,0 -> 7,4
       |6,4 -> 2,0
       |0,9 -> 2,9
       |3,4 -> 1,4
       |0,0 -> 8,8
       |5,5 -> 8,2
       |""".stripMargin
  )

  test("Day05 part one (example)") {
    assertIO(Day05.part1(example), "5")
  }

  test("Day05 part two (example)") {
    assertIO(Day05.part2(example), "12")
  }
