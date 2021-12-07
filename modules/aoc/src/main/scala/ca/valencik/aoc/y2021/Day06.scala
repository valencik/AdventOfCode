package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp, Async}
import cats.syntax.all._
import fs2.{Stream, text}
import ca.valencik.aoc.AdventApp

object Day06 extends AdventApp(2021, 6):
  def intStream[F[_]: Async](in: Stream[F, String]): F[List[Int]] =
    in
      .through(text.lines)
      .filter(s => !s.trim.isEmpty)
      .take(1)
      .compile
      .toList
      .map(ls => ls.flatMap(s => s.split(",").map(_.toInt).toList))

  def dayCycle(fish: List[Int]): List[Int] =
    fish
      .map(_ - 1)
      .flatMap(n =>
        if (n == -1) List(6, 8)
        else List(n)
      )

  def fishToCounts(fish: List[Int]): Vector[Long] =
    val counts = fish.groupMapReduce(identity)(_ => 1)(_ + _)
    (0 to 8).map(counts.getOrElse(_, 0)).map(_.toLong).toVector

  def bigDayCycle(fishCounts: Vector[Long]): Vector[Long] =
    val birthingFish = fishCounts.head
    val shifted      = fishCounts.tail
    shifted.updated(6, shifted(6) + birthingFish).appended(birthingFish)

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in).map(fish => Stream.iterate(fish)(dayCycle).take(80 + 1).toList.last.size.toString)

  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in).map(fish =>
      Stream
        .iterate(fishToCounts(fish))(bigDayCycle)
        .take(256 + 1)
        .takeRight(1)
        .compile
        .toList
        .map(xs => xs.sum)(0)
        .toString
    )
