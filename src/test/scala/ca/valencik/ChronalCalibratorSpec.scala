package ca.valencik

import org.scalatest._

class ChronalCalibratorSpec extends FlatSpec with Matchers {

  "ChronalCalibrator.sumFreq" should "sum number strings" in {
    val nums = List("1", "2", "3").toIterator
    ChronalCalibrator.sumFreq(nums) shouldBe 6
  }

  it should "sum signed number strings" in {
    val nums = List("+1", "-2", "+3").toIterator
    ChronalCalibrator.sumFreq(nums) shouldBe 2
  }
}
