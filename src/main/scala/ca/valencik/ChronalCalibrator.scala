package ca.valencik

import ca.valencik.Utils._

object ChronalCalibrator extends App {

  def process(input: String): Iterable[String] = {
    scala.io.Source.fromFile(input).getLines().toIterable
  }

  def partOne(lines: Iterable[String]): Int = {
    lines.map(_.toInt).sum
  }

  def partTwo(lines: Iterable[String]): Int = {
    val inf    = infiniteStream(lines.map(_.toInt))
    val deltas = inf.scanLeft(0)(_ + _)
    firstDuplicate(deltas)
  }

  putStrLn("partOne: " + partOne(process(args(0))))
  putStrLn("partTwo: " + partTwo(process(args(0))))

}
