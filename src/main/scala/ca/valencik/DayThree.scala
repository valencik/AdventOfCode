package ca.valencik

import ca.valencik.Utils.putStrLn
import Math.abs

object DayThree extends App {
  def ssize(n: Int): Int = {
    (2 * n - 1) * (2 * n - 1)
  }

  def ring(n: Int): Int = {
    def inner(ringAcc: Int): Int = {
      if (n - ssize(ringAcc) <= 0) ringAcc else inner(ringAcc + 1)
    }
    inner(1)
  }

  def compassPoints(r: Int): List[Int] = {
    val e = 4 * (r * r) - 11 * r + 8
    val n = 4 * (r * r) - 9 * r + 6
    val w = 4 * (r * r) - 7 * r + 4
    val s = 4 * (r * r) - 5 * r + 2
    List(e, n, w, s)
  }

  def closestPoint(ps: List[Int], n: Int): Int = {
    ps.map(_ - n).map(Math.abs).min
  }

  def partOne(numString: String): Int = {
    val num = numString.toInt
    val r   = ring(num)
    closestPoint(compassPoints(r), num) + r - 1
  }

  putStrLn("partOne: " + partOne(args(0)))
}
