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

}
