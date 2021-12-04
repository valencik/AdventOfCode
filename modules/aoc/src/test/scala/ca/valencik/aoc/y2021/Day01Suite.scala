import cats.effect.{IO, SyncIO}
import munit.CatsEffectSuite

class Day01Suite extends CatsEffectSuite:
  test("Day01 part one") {
    assertEquals(Day01.p1, 7L)
  }

  test("Day01 part two") {
    assertEquals(Day01.p2, 5L)
  }
