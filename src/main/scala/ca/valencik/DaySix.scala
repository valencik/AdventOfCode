package ca.valencik

import ca.valencik.Utils.putStrLn

object DaySix extends App {
  def work(xs: List[Int]): List[Int] = {
    lazy val currIndex = xs.indexOf(xs.max)
    lazy val value = xs(currIndex)
    lazy val len = xs.length
    lazy val distributionLengthRemainder = value % len
    lazy val equalDistributionAmount = value / len

    def withinJumpRange(i: Int): Boolean = {
      val absJump = currIndex + distributionLengthRemainder
      if (absJump >= len)
        (i > currIndex || i <= (absJump) % len)
      else
        (i > currIndex && i <= absJump )
    }

    xs
      .updated(currIndex, 0)
      .map(_ + equalDistributionAmount)
      .zipWithIndex
      .map{ case (e, i) => {
        if (withinJumpRange(i))
          e+1
        else
          e
      }}
  }

  def partOne(init: List[Int]): Int = {
    def inner(acc: Int, accList: List[List[Int]], curr: List[Int]): Int = {
      if (accList.contains(curr))
        acc
      else
        inner(acc + 1, accList :+ curr, work(curr))
    }
    inner(0, List(List.empty), init)
  }

  def partTwo(init: List[Int]): Int = {
    def inner(acc: Int, accList: List[List[Int]], curr: List[Int]): Int = {
      if (accList.contains(curr))
        accList.length - accList.indexOf(curr)
      else
        inner(acc + 1, accList :+ curr, work(curr))
    }
    inner(0, List(List.empty), init)
  }

  def process(numString: String): List[Int] = numString
    .split("\\s+")
    .map(_.toInt)
    .toList

  putStrLn("partOne: " + partOne(process(args(0))))
  putStrLn("partTwo: " + partTwo(process(args(0))))
}
