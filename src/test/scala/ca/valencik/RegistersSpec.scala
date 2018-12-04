package ca.valencik

import org.scalatest._
import ca.valencik.Registers._

class RegistersSpec extends FlatSpec with Matchers {
  def testInput: Iterator[String] = """b inc 5 if a > 1
        |a inc 1 if b < 5
        |c dec -10 if a >= 1
        |c inc -20 if c == 10""".stripMargin.lines

  "Registers Instr" should "evaluate conditions with empty state" in {
    val instr = Instr("b", "inc", 0, "a", "<=", -1)
    instr.condition(stateNil) shouldEqual false
  }

  it should "support all boolean comparisons" in {
    def compare0and1(comp: String): Boolean = {
      val instr = Instr("b", "inc", 0, "a", comp, 1)
      instr.condition(stateNil)
    }
    compare0and1("==") shouldEqual false
    compare0and1("!=") shouldEqual true
    compare0and1(">=") shouldEqual false
    compare0and1("<=") shouldEqual true
    compare0and1("<") shouldEqual true
    compare0and1(">") shouldEqual false
    compare0and1("NA") shouldEqual false
  }

  it should "return a new State with operate" in {
    val instr = Instr("a", "inc", 1, "b", "==", 0)
    instr.operate(stateNil) shouldEqual stateNil.updated("a", 1)
  }

  it should "return a new State with compute based on condition" in {
    val instrTrue = Instr("a", "inc", 1, "b", "==", 0)
    instrTrue.compute(stateNil) shouldEqual stateNil.updated("a", 1)

    val instrFalse = Instr("a", "inc", 1, "b", ">", 0)
    instrFalse.compute(stateNil) shouldEqual stateNil
  }

  "Registers parseInstructions" should "parse into a List[Instr]" in {
    parseInstructions(testInput.toList) shouldEqual List(
      Instr("b", "inc", 5, "a", ">", 1),
      Instr("a", "inc", 1, "b", "<", 5),
      Instr("c", "dec", -10, "a", ">=", 1),
      Instr("c", "inc", -20, "c", "==", 10)
    )
  }

  "Registers computeInstructions" should "have correct final states" in {
    val instrs     = parseInstructions(testInput.toList)
    val finalState = computeInstructions(instrs)
    finalState.get("a") shouldEqual Some(1)
    finalState.get("c") shouldEqual Some(-10)
    finalState.get("b") shouldEqual None
  }

  "Registers partOne largest register" should "be 1" in {
    partOne(testInput) shouldEqual 1
  }

  "Registers computeHighestRegister" should "be Some(10)" in {
    val instrs = parseInstructions(testInput.toList)
    computeHighestRegister(instrs) shouldEqual Some(10)
  }

  "Registers partTwo largest register" should "be 10" in {
    partTwo(testInput) shouldEqual 10
  }

}
