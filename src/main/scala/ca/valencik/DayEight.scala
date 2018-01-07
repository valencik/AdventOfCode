package ca.valencik

import ca.valencik.Utils.putStrLn
import scala.collection.immutable.HashMap
import scala.util.Try

object DayEight extends App {

  type State = HashMap[String, Int]
  def stateNil: HashMap[String, Int] = HashMap[String, Int]().empty

  case class Instr(
      regA: String,
      op: String,
      value: Int,
      regB: String,
      cond: String,
      condV: Int
  ) {
    def condition(state: State): Boolean = cond match {
      case "==" => state.getOrElse(regB, 0) == condV
      case "!=" => state.getOrElse(regB, 0) != condV
      case ">=" => state.getOrElse(regB, 0) >= condV
      case "<=" => state.getOrElse(regB, 0) <= condV
      case "<"  => state.getOrElse(regB, 0) < condV
      case ">"  => state.getOrElse(regB, 0) > condV
      case _    => false
    }
    def operate(state: State): State = op match {
      case "inc" => state.updated(regA, state.getOrElse(regA, 0) + value)
      case "dec" => state.updated(regA, state.getOrElse(regA, 0) - value)
      case _     => state

    }
    def compute(state: State): State = {
      if (condition(state)) operate(state) else state
    }
  }

  def parseInstructions(lines: List[String]): List[Instr] = {
    lines.flatMap {
      _.split("\\s+") match {
        case Array(ra, o, v, "if", rb, c, cv) =>
          Some(Instr(ra, o, v.toInt, rb, c, cv.toInt))
        case _ => None
      }
    }
  }

  def computeHighestRegister(instrs: List[Instr]): Option[Int] = {
    def inner(acc: Option[Int],
              currInstr: Instr,
              instrs: List[Instr],
              state: State): Option[Int] = {
      val newState        = currInstr.compute(state)
      val newStateHighReg = Try { newState.values.max }.toOption
      val newHighestReg = (acc, newStateHighReg) match {
        case (Some(a), Some(c)) => if (a >= c) Some(a) else Some(c)
        case (None, Some(c))    => Some(c)
        case _                  => None
      }
      if (instrs.isEmpty)
        newHighestReg
      else
        inner(newHighestReg, instrs.head, instrs.tail, newState)
    }
    inner(Some(0), instrs.head, instrs.tail, stateNil)
  }

  def computeInstructions(instrs: List[Instr]): State = {
    def inner(currInstr: Instr, instrs: List[Instr], state: State): State = {
      if (instrs.isEmpty)
        currInstr.compute(state)
      else
        inner(instrs.head, instrs.tail, currInstr.compute(state))
    }
    inner(instrs.head, instrs.tail, stateNil)
  }

  def process(input: String): Iterator[String] = {
    scala.io.Source.fromFile(input).getLines()
  }

  def partOne(lines: Iterator[String]): Int = {
    val finalState = computeInstructions(parseInstructions(lines.toList))
    finalState.values.max
  }

  def partTwo(lines: Iterator[String]): Int = {
    computeHighestRegister(parseInstructions(lines.toList)).get
  }

  putStrLn("partOne: " + partOne(process(args(0))))
  putStrLn("partTwo: " + partTwo(process(args(0))))
}
