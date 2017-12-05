package ca.valencik

import org.scalatest._

class DayFiveSpec extends FlatSpec with Matchers {

  "DayFive partOne for 0 3  0  1  -3" should "return 5" in {
    DayFive.partOne("0 3  0  1  -3") shouldEqual 5
  }

  "DayFive partTwo for 0 3  0  1  -3" should "return 10" in {
    DayFive.partTwo("0 3  0  1  -3") shouldEqual 10
  }
}
