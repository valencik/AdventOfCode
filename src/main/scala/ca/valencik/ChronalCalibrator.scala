package ca.valencik

import ca.valencik.Utils.putStrLn

object ChronalCalibrator extends App {

  def process(input: String): Iterable[String] = {
    scala.io.Source.fromFile(input).getLines().toIterable
  }

  def partOne(lines: Iterable[String]): Int = {
    lines.map(_.toInt).sum
  }

  def infiniteStream[A](xs: Iterable[A]): Stream[A] = {
    lazy val ss: Stream[A] = xs.toStream #::: ss
    ss
  }

  def firstDuplicate[A](xs: Iterable[A]): A = {
    var these: Iterable[A] = xs
    var seen: Set[A]       = Set()
    while (!seen(these.head)) {
      seen += these.head
      these = these.tail
    }
    these.head
  }

  def partTwo(lines: Iterable[String]): Int = {
    val inf    = infiniteStream(lines.map(_.toInt))
    val deltas = inf.scanLeft(0)(_ + _)
    firstDuplicate(deltas)
  }

  putStrLn("partOne: " + partOne(process(args(0))))
  putStrLn("partTwo: " + partTwo(process(args(0))))

}
