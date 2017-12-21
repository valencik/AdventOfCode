package ca.valencik

import org.scalatest._

class DaySevenSpec extends FlatSpec with Matchers {

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

  "DaySeven partOne tree" should "return tknk" in {
    DaySeven.partOne(exampleTree) shouldEqual "tknk"
  }

  "DaySeven childless TreeProper sumWeight" should "return own weight" in {
    val t = DaySeven.TreeProper("test", 1, List())
    t.sumWeight shouldEqual 1
  }

  "DaySeven TreeProper sumWeight" should "return sum of own weight and children" in {
    val t = DaySeven.TreeProper("test",
                                1,
                                List(DaySeven.TreeProper("test1", 1, List()),
                                     DaySeven.TreeProper("test2", 2, List()),
                                     DaySeven.TreeProper("test3", 3, List())))
    t.sumWeight shouldEqual 7
  }

  "DaySeven partTwo tree" should "return 60" in {
    DaySeven.partTwo(exampleTree) shouldEqual 60

  }

}
