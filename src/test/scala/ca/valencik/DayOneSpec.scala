package ca.valencik

import org.scalatest._

class DayOneSpec extends FlatSpec with Matchers {
  "InverseCaptcha partOne for 1122" should "return 3" in {
    DayOne.partOne("1122") shouldEqual 3
  }

  "InverseCaptcha partOne for 1111" should "return 4" in {
    DayOne.partOne("1111") shouldEqual 4
  }

  "InverseCaptcha partOne for 1234" should "return 0" in {
    DayOne.partOne("1234") shouldEqual 0
  }

  "InverseCaptcha partOne for 91212129" should "return 9" in {
    DayOne.partOne("91212129") shouldEqual 9
  }

}
