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

  "InverseCaptcha partTwo for 1212" should "return 6" in {
    DayOne.partTwo("1212") shouldEqual 6
  }

  "InverseCaptcha partTwo for 1221" should "return 0" in {
    DayOne.partTwo("1221") shouldEqual 0
  }

  "InverseCaptcha partTwo for 123425" should "return 4" in {
    DayOne.partTwo("123425") shouldEqual 4
  }

  "InverseCaptcha partTwo for 123123" should "return 12" in {
    DayOne.partTwo("123123") shouldEqual 12
  }

  "InverseCaptcha partTwo for 12131415" should "return 4" in {
    DayOne.partTwo("12131415") shouldEqual 4
  }
}
