package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp, Async}
import cats.syntax.all._
import fs2.{Stream, text}
import ca.valencik.aoc.AdventApp

object Day07 extends AdventApp(2021, 7):
  def intStream[F[_]: Async](in: Stream[F, String]): F[List[Int]] =
    in
      .through(text.lines)
      .filter(s => !s.trim.isEmpty)
      .take(1)
      .compile
      .toList
      .map(ls => ls.flatMap(s => s.split(",").map(_.toInt).toList))

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in).map(ns =>
      val min = ns.min
      val max = ns.max
      (min to max).map(n => ns.map(e => (e - n).abs).sum).min.toString
    )

  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    intStream(in).map(ns =>
      val min = ns.min
      val max = ns.max
      (min to max).map(n => ns.map(e => ((e - n) * (e - n) + ((e - n).abs)) / 2).sum).min.toString
    )
