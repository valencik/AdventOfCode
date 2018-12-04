package ca.valencik

import ca.valencik.Utils.putStrLn

object ChronalCalibrator extends App {

  def sumFreq(freqs: Iterator[String]): Int = {
    freqs.map(_.toInt).sum
  }

  def process(input: String): Iterator[String] = {
    scala.io.Source.fromFile(input).getLines()
  }

  def partOne(lines: Iterator[String]): Int = {
    sumFreq(lines)
  }

  putStrLn("partOne: " + partOne(process(args(0))))

}
