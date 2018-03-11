package ca.valencik

import org.scalatest._
import ca.valencik.DayTen._

class DayTenSpec extends FlatSpec with Matchers {
  "DayTen CircList" should "produce circular lists" in {
    CircList(2).xs.take(4).toList shouldEqual List(0, 1, 0, 1)
    CircList(2).collect(4) shouldEqual List(0, 1, 0, 1)
  }

  it should "implement the knot hash" in {
    val step1 = CircList(5).hash(3)
    step1.collect() shouldEqual List(2, 1, 0, 3, 4)
    step1.collect(10) shouldEqual List(2, 1, 0, 3, 4, 2, 1, 0, 3, 4)
    step1.pos shouldEqual 3
    step1.skip shouldEqual 1
  }

  it should "support composing knot hashes" in {
    val step2 = CircList(5).hash(3).hash(4)
    step2.collect() shouldEqual List(4, 3, 0, 1, 2)
    val step3 = step2.hash(1)
    step3.collect() shouldEqual List(4, 3, 0, 1, 2)
    val step4 = step3.hash(5)
    step4.collect() shouldEqual List(3, 4, 2, 1, 0)
  }

  it should "hashList should compose knot hashes from a list" in {
    val ls = List(3, 4, 1, 5)
    CircList(5).hashList(ls).collect() shouldEqual List(3, 4, 2, 1, 0)
  }

}
