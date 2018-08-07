package ca.valencik

import ca.valencik.Utils.putStrLn
import scala.annotation.tailrec

object DayTen extends App {

  case class CircList(size: Int, pos: Int, skip: Int, xs: Stream[Int]) {
    def hash(length: Int): CircList = CircList(
      size,
      (pos + length + skip) % size,
      skip + 1, {
        val (ys, is) = xs.zipWithIndex.slice(pos, pos + length).unzip
        val ns = xs.zipWithIndex
          .map { case (x, y) => (y, x) }
          .take(size)
          .toMap ++ is.map(_ % size).zip(ys.reverse).toMap
        lazy val ms
          : Stream[Int] = ns.toList.sortBy(_._1).map(_._2).toStream #::: ms
        ms
      }
    )
    def collect(n: Int = size): List[Int] = xs.take(n).toList
    def hashList(lengths: List[Int]): CircList = {
      lengths.foldLeft(this) { case (circ, i) => circ.hash(i) }
    }
    lazy val productOfFirstTwo = xs.take(2).product

    def densifyHash(bits: List[Int]): Iterator[Int] =
      bits
        .grouped(16)
        .map(_.foldLeft(0) { case (acc, i) => acc ^ i })
    lazy val denseHash = densifyHash(this.collect())
  }
  case object CircList {
    def apply(n: Int): CircList = {
      lazy val xs: Stream[Int] = (0 until n).toStream #::: xs
      CircList(n, 0, 0, xs)
    }
  }

  def multiRoundHash(circ: CircList, rounds: Int)(
      lengthList: List[Int]): CircList = {
    @tailrec
    def loop(circ: CircList, rounds: Int): CircList = {
      if (rounds <= 0)
        circ
      else
        loop(circ.hashList(lengthList), rounds - 1)
    }
    loop(circ, rounds)
  }

  def partOne(input: String): Int = {
    val lengthList = input.split(",").toList.map(_.toInt)
    val circ       = CircList(256).hashList(lengthList)
    circ.productOfFirstTwo
  }

  def partTwo(input: String): String = {
    val standardLengthSuffix = List(17, 31, 73, 47, 23)
    val lengthList2          = input.getBytes.toList.map(_.toInt) ++ standardLengthSuffix
    val sparseHash           = multiRoundHash(CircList(256), 64)(lengthList2)
    val denseHash            = sparseHash.denseHash
    val hexString            = denseHash.map("%02x".format(_)).mkString
    hexString
  }

  putStrLn(partOne(args.head).toString)
  putStrLn(partTwo(args.head))

}
