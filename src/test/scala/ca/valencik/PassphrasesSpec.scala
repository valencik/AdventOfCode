package ca.valencik

import org.scalatest._

class PassphrasesSpec extends FlatSpec with Matchers {
  "Passphrases validateDistinct" should "validate strings with all unique words" in {
    Passphrases.validateDistinct("aa bb cc dd ee") shouldEqual true
  }

  it should "not validate when a word is repeated" in {
    Passphrases.validateDistinct("aa bb cc dd aa") shouldEqual false
  }

  it should "validate when a word uses a subset of another word" in {
    Passphrases.validateDistinct("aa bb cc dd aaa") shouldEqual true
  }

  "Passphrases validateAnagrams" should "validate passphrase with unique characters" in {
    Passphrases.validateAnagrams("abcde fghij") shouldEqual true
  }

  it should "not validate when letters in one word can be rearranged to form another provided word" in {
    Passphrases.validateAnagrams("abcde xyz ecdab") shouldEqual false
    Passphrases.validateAnagrams("oiii ioii iioi iiio") shouldEqual false
  }

  it should "validate if one word is a subset of another, because all letters need to be used when forming another word." in {
    Passphrases.validateAnagrams("a ab abc abd abf abj") shouldEqual true
    Passphrases.validateAnagrams("iiii oiii ooii oooi oooo") shouldEqual true
  }

}
