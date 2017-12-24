package ca.valencik

import ca.valencik.DayThree._
import org.scalatest._

class DayThreeSpec extends FlatSpec with Matchers {
  "DayThree partOne data from square 1" should "be carried 0 steps, since it's at the access port." in {
    partOne("1") shouldEqual 0
  }
  "DayThree partOne data from square 12" should "be carried 3 steps, such as: down, left, left." in {
    partOne("12") shouldEqual 3
  }
  "DayThree partOne data from square 23" should "be carried only 2 steps: up twice." in {
    partOne("23") shouldEqual 2
  }
  "DayThree partOne data from square 1024" should "be carried 31 steps. " in {
    partOne("1024") shouldEqual 31
  }

  "DayThree Point(1,1)" should "work" in {
    Point(1, 1).spiralNum shouldEqual 3
    Point(-1, 1).spiralNum shouldEqual 5
    Point(-1, -1).spiralNum shouldEqual 7
    Point(1, -1).spiralNum shouldEqual 9
  }
  "DayThree Point(2,2)" should "work" in {
    Point(2, 2).spiralNum shouldEqual 13
    Point(-2, 2).spiralNum shouldEqual 17
    Point(-2, -2).spiralNum shouldEqual 21
    Point(2, -2).spiralNum shouldEqual 25
  }
  "DayThree Point(2,1)" should "work" in {
    Point(2, 1).spiralNum shouldEqual 12
    Point(-2, 1).spiralNum shouldEqual 18
    Point(-2, -1).spiralNum shouldEqual 20
    Point(2, -1).spiralNum shouldEqual 10
  }
  "DayThree Point(1,2)" should "work" in {
    Point(1, 2).spiralNum shouldEqual 14
    Point(-1, 2).spiralNum shouldEqual 16
    Point(-1, -2).spiralNum shouldEqual 22
    Point(1, -2).spiralNum shouldEqual 24
  }
  "DayThree incrementing Points" should "work" in {
    Point(0, 0).spiralNum shouldEqual 1
    Point(1, 0).spiralNum shouldEqual 2
    Point(1, 1).spiralNum shouldEqual 3
    Point(0, 1).spiralNum shouldEqual 4
    Point(-1, 1).spiralNum shouldEqual 5
    Point(-1, 0).spiralNum shouldEqual 6
    Point(-1, -1).spiralNum shouldEqual 7
    Point(0, -1).spiralNum shouldEqual 8
    Point(1, -1).spiralNum shouldEqual 9
  }
  "DayThree incrementing SpiralNums" should "work" in {
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
  "DayThree numbers 1 to 1000" should "convert to point and back without change" in {
    (1 to 1000).map(SpiralNum(_).point.spiralNum) == (1 to 1000)
  }
}
