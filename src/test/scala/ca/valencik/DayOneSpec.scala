package ca.valencik

import org.scalatest._

class DayOneSpec extends FlatSpec with Matchers {
  "InverseCaptcha for 1122" should "return 3" in {
    DayOne.apply("1122") shouldEqual 3
  }

  "InverseCaptcha for 1111" should "return 4" in {
    DayOne.apply("1111") shouldEqual 4
  }

  "InverseCaptcha for 1234" should "return 0" in {
    DayOne.apply("1234") shouldEqual 0
  }

  "InverseCaptcha for 91212129" should "return 9" in {
    DayOne.apply("91212129") shouldEqual 9
  }

}
