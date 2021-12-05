package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp, Async}
import cats.syntax.all._
import fs2.{Stream, text}
import ca.valencik.aoc.AdventApp

object Day01 extends AdventApp(2021, 1):
  def intStream[F[_]: Async](in: Stream[F, String]): Stream[F, Int] =
    in
      .through(text.lines)
      .filter(s => !s.trim.isEmpty)
      .map(_.toInt)

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in)
      .sliding(2)
      .filter(p => p(1) > p(0))
      .compile
      .count
      .map(_.toString)

  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in)
      .sliding(3)
      .map(_.combineAll)
      .sliding(2)
      .filter(p => p(1) > p(0))
      .compile
      .count
      .map(_.toString)
