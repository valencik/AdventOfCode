package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp}
import cats.syntax.all._
import fs2.Stream

object Day01 extends IOApp.Simple:
  val xs = List(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

  val p1 = Stream
    .emits(xs)
    .sliding(2)
    .filter(p => p(1) > p(0))
    .compile
    .count

  val p2 = Stream
    .emits(xs)
    .sliding(3)
    .map(_.combineAll)
    .sliding(2)
    .filter(p => p(1) > p(0))
    .compile
    .count

  val run: IO[Unit] =
    IO.println(s"Part one: $p1") *>
      IO.println(s"Part two: $p2")
