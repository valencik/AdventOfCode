package ca.valencik

import ca.valencik.Utils.putStrLn

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
  }
  case object CircList {
    def apply(n: Int): CircList = {
      lazy val xs: Stream[Int] = (0 until n).toStream #::: xs
      CircList(n, 0, 0, xs)
    }
  }

  val lengthList = args.head.split(",").toList.map(_.toInt)
  val circ       = CircList(256).hashList(lengthList)
  putStrLn(circ.productOfFirstTwo.toString)

}
