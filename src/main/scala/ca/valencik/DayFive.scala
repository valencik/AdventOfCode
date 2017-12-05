package ca.valencik

import ca.valencik.Utils.putStrLn

import scala.annotation.tailrec

object DayFive extends App {
  def executeJumpInstructions(instructions: List[Int]): Int = {
    val size = instructions.length

    @tailrec
    def inner(acc: Int, is: List[Int], c: Int): Int = {
      val jump = is(c)
      if (c + jump >= size)
        acc + 1
      else
        inner(acc + 1, is.updated(c, jump + 1), c + jump)
    }
    inner(0, instructions, 0)
  }

  def executeStrangerJumpInstructions(instructions: List[Int]): Int = {
    val size = instructions.length

    @tailrec
    def inner(acc: Int, is: List[Int], c: Int): Int = {
      val jump = is(c)
      val incr = if (jump >= 3) -1 else 1
      if (c + jump >= size)
        acc + 1
      else
        inner(acc + 1, is.updated(c, jump + incr), c + jump)
    }
    inner(0, instructions, 0)
  }

  def process(input: String): List[Int] = {
    input.split("\\s+").map(_.toInt).toList
  }

  def partOne(numString: String): Int = {
    executeJumpInstructions(process(numString))
  }

  def partTwo(numString: String): Int = {
    executeStrangerJumpInstructions(process(numString))
  }

  putStrLn("partOne: " + partOne(args(0)))
  putStrLn("partTwo: " + partTwo(args(0)))
}
