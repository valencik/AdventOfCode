package ca.valencik

import org.scalatest._
import ca.valencik.DayNine._

class DayNineSpec extends FlatSpec with Matchers {

  "DayNine State.process" should "detect pure garbage" in {
    val pureGarbage = State.empty
    State.process("<>") shouldEqual pureGarbage
    State.process("<random characters>") shouldEqual pureGarbage
    State.process("<<<<>") shouldEqual pureGarbage
    State.process("<{!>}>") shouldEqual pureGarbage
    State.process("<!!>") shouldEqual pureGarbage
    State.process("<!!!>>") shouldEqual pureGarbage
    State.process("""<{o"i!a,<{i<a>""") shouldEqual pureGarbage
  }

  "DayNine State.process" should "return score that is sum of group depths" in {
    State.process("{}").score shouldEqual 1
    State.process("{{{}}}").score shouldEqual 6
    State.process("{{},{}}").score shouldEqual 5
    State.process("{{{},{},{{}}}}").score shouldEqual 16
    State.process("{<a>,<a>,<a>,<a>}").score shouldEqual 1
    State.process("{{<ab>},{<ab>},{<ab>},{<ab>}}").score shouldEqual 9
    State.process("{{<!!>},{<!!>},{<!!>},{<!!>}}").score shouldEqual 9
    State.process("{{<a!>},{<a!>},{<a!>},{<ab>}}").score shouldEqual 3
  }

}
