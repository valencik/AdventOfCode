package ca.valencik

import org.scalatest._

class DayThreeSpec extends FlatSpec with Matchers {
  "DayThree partOne data from square 1" should "be carried 0 steps, since it's at the access port." in {
    DayThree.partOne("1") shouldEqual 0
  }
  "DayThree partOne data from square 12" should "be carried 3 steps, such as: down, left, left." in {
    DayThree.partOne("12") shouldEqual 3
  }
  "DayThree partOne data from square 23" should "be carried only 2 steps: up twice." in {
    DayThree.partOne("23") shouldEqual 2
  }
  "DayThree partOne data from square 1024" should "be carried 31 steps. " in {
    DayThree.partOne("1024") shouldEqual 31
  }

  "DayThree Point(1,1)" should "work" in {
    DayThree.Point(1, 1).spiralNum shouldEqual 3
    DayThree.Point(-1, 1).spiralNum shouldEqual 5
    DayThree.Point(-1, -1).spiralNum shouldEqual 7
    DayThree.Point(1, -1).spiralNum shouldEqual 9
  }
  "DayThree Point(2,2)" should "work" in {
    DayThree.Point(2, 2).spiralNum shouldEqual 13
    DayThree.Point(-2, 2).spiralNum shouldEqual 17
    DayThree.Point(-2, -2).spiralNum shouldEqual 21
    DayThree.Point(2, -2).spiralNum shouldEqual 25
  }
  "DayThree Point(2,1)" should "work" in {
    DayThree.Point(2, 1).spiralNum shouldEqual 12
    DayThree.Point(-2, 1).spiralNum shouldEqual 18
    DayThree.Point(-2, -1).spiralNum shouldEqual 20
    DayThree.Point(2, -1).spiralNum shouldEqual 10
  }
  "DayThree Point(1,2)" should "work" in {
    DayThree.Point(1, 2).spiralNum shouldEqual 14
    DayThree.Point(-1, 2).spiralNum shouldEqual 16
    DayThree.Point(-1, -2).spiralNum shouldEqual 22
    DayThree.Point(1, -2).spiralNum shouldEqual 24
  }
  "DayThree incrementing Points" should "work" in {
    DayThree.Point(0, 0).spiralNum shouldEqual 1
    DayThree.Point(1, 0).spiralNum shouldEqual 2
    DayThree.Point(1, 1).spiralNum shouldEqual 3
    DayThree.Point(0, 1).spiralNum shouldEqual 4
    DayThree.Point(-1, 1).spiralNum shouldEqual 5
    DayThree.Point(-1, 0).spiralNum shouldEqual 6
    DayThree.Point(-1, -1).spiralNum shouldEqual 7
    DayThree.Point(0, -1).spiralNum shouldEqual 8
    DayThree.Point(1, -1).spiralNum shouldEqual 9
  }

}
