package ca.valencik

import org.scalatest._

class MemoryReallocationSpec extends FlatSpec with Matchers {
  "MemoryReallocation partOne 0,2,7,0" should "return 5" in {
    MemoryReallocation.partOne("0\t2\t7\t0") shouldEqual 5
  }

  "MemoryReallocation partTwo 0,2,7,0" should "return 4" in {
    MemoryReallocation.partTwo("0\t2\t7\t0") shouldEqual 4
  }
}
