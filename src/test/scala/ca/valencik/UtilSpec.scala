package ca.valencik

import org.scalatest._
import ca.valencik.Utils._

class UtilSpec extends FlatSpec with Matchers {

  "Utils infiniteStream" should "repeat an Iterable" in {
    infiniteStream(0 until 2).take(4).toList shouldBe List(0, 1, 0, 1)
  }

  "Utils firstDuplicate" should "return the first duplicate element" in {
    firstDuplicate(List(1, 1)) shouldBe 1
    firstDuplicate(List(1, 2, 3, 4, 4, 5)) shouldBe 4
    firstDuplicate(9999 +: (1 until 10000)) shouldBe 9999
  }
}
