package ca.valencik

import org.scalatest._
import ca.valencik.KnotHash._

class KnotHashSpec extends FlatSpec with Matchers {
  "KnotHash CircList" should "produce circular lists" in {
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

  "KnotHash partOne" should "produce correct product of first two numbers" in {
    partOne("147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70") shouldBe 37230
  }

  "KnotHash multiRoundHash" should "degrade in number of hashes performed" in {
    val input = List(3, 4, 1, 5)
    multiRoundHash(CircList(5), 0)(input).collect() shouldBe List(0, 1, 2, 3, 4)
    multiRoundHash(CircList(5), 1)(input).collect() shouldBe List(3, 4, 2, 1, 0)
  }

  "KnotHash partTwo" should "produce expected hexStrings" in {
    partTwo("") shouldBe "a2582a3a0e66e6e86e3812dcb672a272"
    partTwo("AoC 2017") shouldBe "33efeb34ea91902bb2f59c9920caa6cd"
    partTwo("1,2,3") shouldBe "3efbe78a8d82f29979031a4aa0b16a9d"
    partTwo("1,2,4") shouldBe "63960835bcdc130f0b66d7ff4f6a5a8e"
    partTwo("147,37,249,1,31,2,226,0,161,71,254,243,183,255,30,70") shouldBe "70b856a24d586194331398c7fcfa0aaf"
  }

}
