package ca.valencik

import org.scalatest._

class InverseCaptchaSpec extends FlatSpec with Matchers {
  "InverseCaptcha" should "implement partOne InverseCaptcha" in {
    InverseCaptcha.partOne("1122") shouldEqual 3
    InverseCaptcha.partOne("1111") shouldEqual 4
    InverseCaptcha.partOne("1234") shouldEqual 0
    InverseCaptcha.partOne("91212129") shouldEqual 9
  }

  it should "implement partTwo InverseCaptcha" in {
    InverseCaptcha.partTwo("1212") shouldEqual 6
    InverseCaptcha.partTwo("1221") shouldEqual 0
    InverseCaptcha.partTwo("123425") shouldEqual 4
    InverseCaptcha.partTwo("123123") shouldEqual 12
    InverseCaptcha.partTwo("12131415") shouldEqual 4
  }
}
