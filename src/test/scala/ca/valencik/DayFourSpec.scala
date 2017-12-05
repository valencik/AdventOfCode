package ca.valencik

import org.scalatest._

class DayFourSpec extends FlatSpec with Matchers {
  "DayFour partOne for aa bb cc dd ee" should "be valid." in {
    DayFour.validateDistinct("aa bb cc dd ee") shouldEqual true
  }

  "DayFour partOne for aa bb cc dd aa" should "be not valid - the word aa appears more than once." in {
    DayFour.validateDistinct("aa bb cc dd aa") shouldEqual false
  }

  "DayFour partOne for aa bb cc dd aaa" should "be valid - aa and aaa count as different words." in {
    DayFour.validateDistinct("aa bb cc dd aaa") shouldEqual true
  }

    "DayFour partTwo for abcde fghij" should "be a valid passphrase." in {
    DayFour.validateAnagrams("abcde fghij") shouldEqual true
  }

    "DayFour partTwo for abcde xyz ecdab" should "not be valid - the letters from the third word can be rearranged to form the first word." in {
    DayFour.validateAnagrams("abcde xyz ecdab") shouldEqual false
  }

    "DayFour partTwo for a ab abc abd abf abj" should "be a valid passphrase, because all letters need to be used when forming another word." in {
    DayFour.validateAnagrams("a ab abc abd abf abj") shouldEqual true
  }

    "DayFour partTwo for iiii oiii ooii oooi oooo" should "be valid." in {
    DayFour.validateAnagrams("iiii oiii ooii oooi oooo") shouldEqual true
  }

    "DayFour partTwo for oiii ioii iioi iiio" should "be not valid - any of these words can be rearranged to form any other word." in {
    DayFour.validateAnagrams("oiii ioii iioi iiio") shouldEqual false
  }

}
