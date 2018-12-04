package ca.valencik

import org.scalatest._

class CorruptionChecksumSpec extends FlatSpec with Matchers {
  "CorruptionChecksum" should "implement partOne grid" in {
    CorruptionChecksum.partOne("""
      5 1 9 5
      7 5 3
      2 4 6 8
      """) shouldEqual 18
  }

  it should "implement partTwo grid" in {
    CorruptionChecksum.partTwo("""
      5 9 2 8
      9 4 7 3
      3 8 6 5
      """) shouldEqual 9
  }
}
