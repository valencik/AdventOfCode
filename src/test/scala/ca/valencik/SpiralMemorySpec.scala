package ca.valencik

import ca.valencik.SpiralMemory._
import org.scalatest._

class SpiralMemorySpec extends FlatSpec with Matchers {
  "SpiralMemory partOne" should "carry data from square 1 zero steps, since it's at the access port." in {
    partOne("1") shouldEqual 0
  }
  it should "carry data from square 12 three steps, such as: down, left, left." in {
    partOne("12") shouldEqual 3
  }
  it should "carry data from square 23 only two steps: up twice." in {
    partOne("23") shouldEqual 2
  }
  it should "carry date from square 1024 thirty one steps. " in {
    partOne("1024") shouldEqual 31
  }

  "SpiralMemory spiralNum" should "work" in {
    Point(0, 0).spiralNum shouldEqual 1
    Point(1, 0).spiralNum shouldEqual 2
    Point(1, 1).spiralNum shouldEqual 3
    Point(0, 1).spiralNum shouldEqual 4
    Point(-1, 1).spiralNum shouldEqual 5
    Point(-1, 0).spiralNum shouldEqual 6
    Point(-1, -1).spiralNum shouldEqual 7
    Point(0, -1).spiralNum shouldEqual 8
    Point(1, -1).spiralNum shouldEqual 9
    Point(2, 2).spiralNum shouldEqual 13
    Point(-2, 2).spiralNum shouldEqual 17
    Point(-2, -2).spiralNum shouldEqual 21
    Point(2, -2).spiralNum shouldEqual 25
    Point(2, 1).spiralNum shouldEqual 12
    Point(-2, 1).spiralNum shouldEqual 18
    Point(-2, -1).spiralNum shouldEqual 20
    Point(2, -1).spiralNum shouldEqual 10
    Point(1, 2).spiralNum shouldEqual 14
    Point(-1, 2).spiralNum shouldEqual 16
    Point(-1, -2).spiralNum shouldEqual 22
    Point(1, -2).spiralNum shouldEqual 24
  }
  "SpiralMemory incrementing SpiralNums" should "work" in {
    SpiralNum(1).point shouldEqual Point(0, 0)
    SpiralNum(2).point shouldEqual Point(1, 0)
    SpiralNum(3).point shouldEqual Point(1, 1)
    SpiralNum(4).point shouldEqual Point(0, 1)
    SpiralNum(5).point shouldEqual Point(-1, 1)
    SpiralNum(6).point shouldEqual Point(-1, 0)
    SpiralNum(7).point shouldEqual Point(-1, -1)
    SpiralNum(8).point shouldEqual Point(0, -1)
    SpiralNum(9).point shouldEqual Point(1, -1)
  }
  "SpiralMemory numbers 1 to 1000" should "convert to point and back without change" in {
    (1 to 1000).map(SpiralNum(_).point.spiralNum) == (1 to 1000)
  }
  "SpiralMemory squareSpiralSums(1 to 10)" should "work" in {
    (1 to 10).map(squareSpiralSums).toList shouldEqual List(1, 1, 2, 4, 5, 10,
      11, 23, 25, 26)
  }
  "SpiralMemory partTwo(325)" should "be 330" in {
    partTwo("325") shouldEqual 330
  }
}
