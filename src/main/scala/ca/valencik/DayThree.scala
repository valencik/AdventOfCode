package ca.valencik

import ca.valencik.Utils.putStrLn
import Math.{abs, max, min}

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

  case class Compass(e: Int, n: Int, w: Int, s: Int)
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

  case class Point(x: Int, y: Int) {
    lazy val quadrant = {
      if (x >= 0 && y >= 0) 1
      else if (x < 0 && y >= 0) 2
      else if (x < 0 && y < 0) 3
      else 4
    }
    lazy val r      = max(abs(x), abs(y))
    lazy val cp     = compassPoints(r + 1)
    lazy val comp   = Compass(cp(0), cp(1), cp(2), cp(3))
    lazy val majorX = abs(x) > abs(y)
    lazy val majorY = abs(y) >= abs(x)
    def spiralNum: Int = {
      if (x == 0 && y == 0) 1
      else if (quadrant == 1) if (majorX) comp.e + y else comp.n - x
      else if (quadrant == 2) if (majorX) comp.w - y else comp.n - x
      else if (quadrant == 3) if (majorX) comp.w - y else comp.s + x
      else if (majorX) comp.e + y
      else comp.s + x
    }
  }

  case class SpiralNum(n: Int) {
    lazy val r    = ring(n) - 1
    lazy val cp   = compassPoints(r + 1)
    lazy val comp = Compass(cp(0), cp(1), cp(2), cp(3))
    lazy val aComp =
      Compass(abs(cp(0) - n), abs(cp(1) - n), abs(cp(2) - n), abs(cp(3) - n))
    lazy val dComp = Compass(cp(0) - n, cp(1) - n, cp(2) - n, cp(3) - n)
    lazy val cMin  = List(aComp.e, aComp.n, aComp.w, aComp.s).min
    lazy val point: Point = {
      if (aComp.e == cMin) Point(r, -dComp.e)
      else if (aComp.w == cMin) Point(-r, dComp.w)
      else if (aComp.n == cMin) Point(dComp.n, r)
      else Point(-dComp.s, -r)
    }

  }

  def partOne(numString: String): Int = {
    val num = numString.toInt
    val r   = ring(num)
    closestPoint(compassPoints(r), num) + r - 1
  }

  putStrLn("partOne: " + partOne(args(0)))
}
