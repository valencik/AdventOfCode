package ca.valencik

import org.scalatest._

class DayOneSpec extends FlatSpec with Matchers {
  "DayOne" should "implement partOne InverseCaptcha" in {
    DayOne.partOne("1122") shouldEqual 3
    DayOne.partOne("1111") shouldEqual 4
    DayOne.partOne("1234") shouldEqual 0
    DayOne.partOne("91212129") shouldEqual 9
  }

  it should "implement partTwo InverseCaptcha" in {
    DayOne.partTwo("1212") shouldEqual 6
    DayOne.partTwo("1221") shouldEqual 0
    DayOne.partTwo("123425") shouldEqual 4
    DayOne.partTwo("123123") shouldEqual 12
    DayOne.partTwo("12131415") shouldEqual 4
  }
}
