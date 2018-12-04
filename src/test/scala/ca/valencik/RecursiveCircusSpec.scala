package ca.valencik

import org.scalatest._

class RecursiveCircusSpec extends FlatSpec with Matchers {

  def exampleTree: Iterator[String] = """pbga (66)
                 |xhth (57)
                 |ebii (61)
                 |havc (66)
                 |ktlj (57)
                 |fwft (72) -> ktlj, cntj, xhth
                 |qoyq (66)
                 |padx (45) -> pbga, havc, qoyq
                 |tknk (41) -> ugml, padx, fwft
                 |jptl (61)
                 |ugml (68) -> gyxo, ebii, jptl
                 |gyxo (61)
                 |cntj (57)""".stripMargin.lines

  "RecursiveCircus partOne tree" should "return tknk" in {
    RecursiveCircus.partOne(exampleTree) shouldEqual "tknk"
  }

  "RecursiveCircus childless TreeProper sumWeight" should "return own weight" in {
    val t = RecursiveCircus.TreeProper("test", 1, List())
    t.sumWeight shouldEqual 1
  }

  "RecursiveCircus TreeProper sumWeight" should "return sum of own weight and children" in {
    val t = RecursiveCircus.TreeProper("test",
                                1,
                                List(RecursiveCircus.TreeProper("test1", 1, List()),
                                     RecursiveCircus.TreeProper("test2", 2, List()),
                                     RecursiveCircus.TreeProper("test3", 3, List())))
    t.sumWeight shouldEqual 7
  }

  "RecursiveCircus partTwo tree" should "return 60" in {
    RecursiveCircus.partTwo(exampleTree) shouldEqual 60

  }

}
