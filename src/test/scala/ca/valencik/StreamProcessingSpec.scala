package ca.valencik

import org.scalatest._
import ca.valencik.StreamProcessing._

class StreamProcessingSpec extends FlatSpec with Matchers {

  "StreamProcessing State.process" should "detect pure garbage and have zero score" in {
    State.process("<>").score shouldEqual 0
    State.process("<random characters>").score shouldEqual 0
    State.process("<<<<>").score shouldEqual 0
    State.process("<{!>}>").score shouldEqual 0
    State.process("<!!>").score shouldEqual 0
    State.process("<!!!>>").score shouldEqual 0
    State.process("""<{o"i!a,<{i<a>""").score shouldEqual 0
  }

  "StreamProcessing State.process" should "return score that is sum of group depths" in {
    State.process("{}").score shouldEqual 1
    State.process("{{{}}}").score shouldEqual 6
    State.process("{{},{}}").score shouldEqual 5
    State.process("{{{},{},{{}}}}").score shouldEqual 16
    State.process("{<a>,<a>,<a>,<a>}").score shouldEqual 1
    State.process("{{<ab>},{<ab>},{<ab>},{<ab>}}").score shouldEqual 9
    State.process("{{<!!>},{<!!>},{<!!>},{<!!>}}").score shouldEqual 9
    State.process("{{<a!>},{<a!>},{<a!>},{<ab>}}").score shouldEqual 3
  }

  "StreamProcessing State.process" should "return total garbage collected" in {
    State.process("<>").garbageCount shouldEqual 0
    State.process("<random characters>").garbageCount shouldEqual 17
    State.process("<<<<>").garbageCount shouldEqual 3
    State.process("<{!>}>").garbageCount shouldEqual 2
    State.process("<!!>").garbageCount shouldEqual 0
    State.process("<!!!>>").garbageCount shouldEqual 0
    State.process("""<{o"i!a,<{i<a>""").garbageCount shouldEqual 10
  }

}
