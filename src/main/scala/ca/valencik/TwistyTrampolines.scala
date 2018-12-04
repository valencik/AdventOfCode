package ca.valencik

import ca.valencik.Utils.{putStrLn, perfTime}

import scala.annotation.tailrec
import scala.io.Source
import scala.collection.mutable.ArrayOps

object TwistyTrampolines extends App {
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

  def executeJumpInstructionsArray(instructions: Array[Int]): Int = {
    val size = instructions.length

    @tailrec
    def inner(acc: Int, c: Int): Int = {
      val jump = instructions(c)
      if (c + jump >= size)
        acc + 1
      else {
        instructions.update(c, jump + 1)
        inner(acc + 1, c + jump)
      }
    }
    inner(0, 0)
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

  def executeStrangerJumpInstructionsArray(instructions: Array[Int]): Int = {
    val size = instructions.length

    @tailrec
    def inner(acc: Int, c: Int): Int = {
      val jump = instructions(c)
      val incr = if (jump >= 3) -1 else 1
      if (c + jump >= size)
        acc + 1
      else {
        instructions.update(c, jump + incr)
        inner(acc + 1, c + jump)
      }
    }
    inner(0, 0)
  }

  def process(input: String): Iterator[Int] = {
    Source
      .fromFile(input)
      .getLines
      .map(_.split("\\s+").map(_.toInt))
      .flatten
  }

  def partOneArray(numString: String): Int = {
    val data = process(numString).toArray
    perfTime {
      executeJumpInstructionsArray(data)
    }
  }

  def partTwoArray(numString: String): Int = {
    val data = process(numString).toArray
    perfTime {
      executeStrangerJumpInstructionsArray(data)
    }
  }

  putStrLn("partOne: " + partOneArray(args(0)))
  putStrLn("partTwo: " + partTwoArray(args(0)))
}
