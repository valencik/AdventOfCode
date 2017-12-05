package ca.valencik

import ca.valencik.Utils.putStrLn

object DayFour extends App {
  def validateDistinct(numString: String): Boolean = {
    val numList = numString.split(" ")
    numList.distinct.length == numList.length
  }

  def validateAnagrams(numString: String): Boolean = {
    val numList = numString.split(" ")
    numList.map(_.toList.sorted.mkString).distinct.length == numList.length
  }

  def partOne(input: String): Int = {
    input.lines.map(validateDistinct).filter(identity).length
  }

  def partTwo(input: String): Int = {
    input.lines.map(validateAnagrams).filter(identity).length
  }

  putStrLn("partOne: " + partOne(args(0)))
  putStrLn("partTwo: " + partTwo(args(0)))
}
