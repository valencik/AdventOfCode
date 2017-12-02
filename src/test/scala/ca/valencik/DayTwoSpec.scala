package ca.valencik

import org.scalatest._

class DayTwoSpec extends FlatSpec with Matchers {
  "DayTwo partOne grid" should "return 18" in {
    DayTwo.partOne("""
      5 1 9 5
      7 5 3
      2 4 6 8
      """) shouldEqual 18
  }

  "DayTwo partTwo grid" should "return 9" in {
    DayTwo.partTwo("""
      5 9 2 8
      9 4 7 3
      3 8 6 5
      """) shouldEqual 9
  }
}
