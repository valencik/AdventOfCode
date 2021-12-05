package ca.valencik.aoc.y2021

import cats.effect.IO
import fs2.Stream
import munit.CatsEffectSuite

class Day01Suite extends CatsEffectSuite:
  val example = Stream(
    """|199
       |200
       |208
       |210
       |200
       |207
       |240
       |269
       |260
       |263
       |""".stripMargin
  )

  test("Day01 part one (example)") {
    assertIO(Day01.part1(example), "7")
  }

  test("Day01 part two (example)") {
    assertIO(Day01.part2(example), "5")
  }
