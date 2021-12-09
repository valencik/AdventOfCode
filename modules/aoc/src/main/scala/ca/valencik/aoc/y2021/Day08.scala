package ca.valencik.aoc.y2021

import cats.effect.{IO, IOApp, Async}
import cats.syntax.all._
import fs2.{Stream, text}
import scala.util.chaining._
import ca.valencik.aoc.AdventApp

object Day08 extends AdventApp(2021, 8):
  def lineStream[F[_]: Async](in: Stream[F, String]): Stream[F, String] =
    in
      .through(text.lines)
      .filter(s => !s.trim.isEmpty)

  def parse(s: String) =
    val lengthPatterns = Set(2, 3, 4, 7)
    s.split(raw" \| ")(1)
      .split(" ")
      .map(_.length)
      .filter(n => lengthPatterns.contains(n))
      .size

  def decode(s: String): Map[Set[Char], Int] =
    val sets  = s.split(raw" \| ")(0).split(" ").map(x => x.toSet).toList
    val one   = sets.indexWhere(_.size == 2).pipe(sets)
    val four  = sets.indexWhere(_.size == 4).pipe(sets)
    val seven = sets.indexWhere(_.size == 3).pipe(sets)
    val eight = sets.indexWhere(_.size == 7).pipe(sets)

    val three = sets.indexWhere(s => s.size == 5 && seven.subsetOf(s)).pipe(sets)
    val nine  = sets.indexWhere(s => s.size == 6 && three.subsetOf(s)).pipe(sets)

    val zero = sets
      .indexWhere(s => s.size == 6 && seven.subsetOf(s) && !s.subsetOf(nine))
      .pipe(sets)

    val six = sets
      .indexWhere(s => s.size == 6 && !s.subsetOf(zero) && !s.subsetOf(nine))
      .pipe(sets)

    val five = sets.indexWhere(s => s.size == 5 && s.subsetOf(six)).pipe(sets)

    val two = sets
      .indexWhere(s => s.size == 5 && !s.subsetOf(three) && !s.subsetOf(five))
      .pipe(sets)
    Map(
      one   -> 1,
      two   -> 2,
      three -> 3,
      four  -> 4,
      five  -> 5,
      six   -> 6,
      seven -> 7,
      eight -> 8,
      nine  -> 9,
      zero  -> 0
    )

  def lookup(s: String): Int =
    val decoderMap = decode(s)
    val sets       = s.split(raw" \| ")(1).split(" ").map(x => x.toSet).toList
    val nums       = sets.map(decoderMap(_))
    nums.mkString.pipe(_.toInt)

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    lineStream(in).map(parse).compile.toList.map(_.sum.toString)

  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    lineStream(in).map(lookup).compile.toList.map(_.sum.toString)
