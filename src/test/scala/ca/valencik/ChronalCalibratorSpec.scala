package ca.valencik

import org.scalatest._

class ChronalCalibratorSpec extends FlatSpec with Matchers {

  "ChronalCalibrator partOne" should "sum number strings" in {
    val nums = List("1", "2", "3")
    ChronalCalibrator.partOne(nums) shouldBe 6
  }

  it should "sum signed number strings" in {
    val nums = List("+1", "-2", "+3")
    ChronalCalibrator.partOne(nums) shouldBe 2
  }

  "ChronalCalibrator partTwo" should "return the first duplicate state" in {
    ChronalCalibrator.partTwo(Seq("+1", "-1")) shouldBe 0
    ChronalCalibrator.partTwo(Seq("+3", "+3", "+4", "-2", "-4")) shouldBe 10
    ChronalCalibrator.partTwo(Seq("-6", "+3", "+8", "+5", "-6")) shouldBe 5
    ChronalCalibrator.partTwo(Seq("+7", "+7", "-2", "-7", "-4")) shouldBe 14
  }
}
