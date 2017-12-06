package ca.valencik

import org.scalatest._

class DayFiveSpec extends FlatSpec with Matchers {

  "DayFive partOne (List) for [0 3  0  1  -3]" should "return 5" in {
    DayFive.executeJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 5
  }

  "DayFive partOne (Array) for [0 3  0  1  -3]" should "return 5" in {
    DayFive.executeJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 5
  }

  "DayFive partTwo (List) for [0 3  0  1  -3]" should "return 10" in {
    DayFive.executeStrangerJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 10
  }

  "DayFive partTwo (Array) for [0 3  0  1  -3]" should "return 10" in {
    DayFive.executeStrangerJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 10
  }
}
