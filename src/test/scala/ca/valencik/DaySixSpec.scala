package ca.valencik

import org.scalatest._

class DaySixSpec extends FlatSpec with Matchers {
  "DaySix partOne 0,2,7,0" should "return 5" in {
    DaySix.partOne("0\t2\t7\t0") shouldEqual 5
  }

  "DaySix partTwo 0,2,7,0" should "return 4" in {
    DaySix.partTwo("0\t2\t7\t0") shouldEqual 4
  }
}
