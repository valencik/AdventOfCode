package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp, Async}
import cats.syntax.all._
import fs2.{Stream, text}
import ca.valencik.aoc.AdventApp
import scala.util.chaining._

object Day05 extends AdventApp(2021, 5):
  def inclusiveRange(n1: Int, n2: Int): List[Int] =
    if (n1 <= n2)
      Range.inclusive(n1, n2).toList
    else
      Range.inclusive(n2, n1).reverse.toList

  def slope(p1: (Int, Int), p2: (Int, Int)): Int =
    (p2._2 - p1._2) / (p2._1 - p1._1)

  def tuple2ToPoints(p1: (Int, Int), p2: (Int, Int)): List[(Int, Int)] =
    if (p1._1 == p2._1)
      inclusiveRange(p1._2, p2._2).map((p1._1, _))
    else if (p1._2 == p2._2)
      inclusiveRange(p1._1, p2._1).map((_, p1._2))
    else if (slope(p1, p2).abs == 1)
      inclusiveRange(p1._1, p2._1) zip inclusiveRange(p1._2, p2._2)
    else List.empty

  def countOverlaps(ps: List[(Int, Int)]): Int =
    ps.groupMapReduce(identity)(_ => 1)(_ + _)
      .valuesIterator
      .count(_ >= 2)

  def segmentStream[F[_]: Async](in: Stream[F, String]): Stream[F, ((Int, Int), (Int, Int))] =
    in
      .through(text.lines)
      .filter(s => !s.trim.isEmpty)
      .map(s =>
        s.split(" -> ")
          .map(_.split(",").map(_.toInt))
          .pipe(x => ((x(0)(0), x(0)(1)), (x(1)(0), x(1)(1))))
      )

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    segmentStream(in)
      .filter((p1, p2) => p1._1 == p2._1 || p1._2 == p2._2)
      .flatMap(tuple2ToPoints(_, _).pipe(Stream.emits))
      .compile
      .toList
      .map(countOverlaps)
      .map(_.toString)

  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    segmentStream(in)
      .flatMap(tuple2ToPoints(_, _).pipe(Stream.emits))
      .compile
      .toList
      .map(countOverlaps)
      .map(_.toString)
