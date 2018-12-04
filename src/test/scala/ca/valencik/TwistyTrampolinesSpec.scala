package ca.valencik

import org.scalatest._

class TwistyTrampolinesSpec extends FlatSpec with Matchers {

  "TwistyTrampolines partOne" should "implement Jump Instructions for Lists" in {
    TwistyTrampolines.executeJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 5
  }

  it should "implement Jump Instructions for Arrays" in {
    TwistyTrampolines.executeJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 5
  }

  "TwistyTrampolines partTwo" should "implement Stranger Jump Instructions for Lists" in {
    TwistyTrampolines.executeStrangerJumpInstructions(List(0, 3, 0, 1, -3)) shouldEqual 10
  }

  it should "implement Stranger Jump Instructions for Arrays" in {
    TwistyTrampolines.executeStrangerJumpInstructionsArray(Array(0, 3, 0, 1, -3)) shouldEqual 10
  }
}
