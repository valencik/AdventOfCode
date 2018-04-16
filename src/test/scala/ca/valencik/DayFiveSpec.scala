package ca.valencik

import org.scalatest._

class DayFiveSpec extends FlatSpec with Matchers {

  "DayFive partOne" should "implement Jump Instructions for Lists" in {
    DayFive.executeJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 5
  }

  it should "implement Jump Instructions for Arrays" in {
    DayFive.executeJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 5
  }

  "DayFive partTwo" should "implement Stranger Jump Instructions for Lists" in {
    DayFive.executeStrangerJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 10
  }

  it should "implement Stranger Jump Instructions for Arrays" in {
    DayFive.executeStrangerJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 10
  }
}
