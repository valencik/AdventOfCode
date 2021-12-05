package ca.valencik.aoc.y2021

import scala.concurrent.duration._
import scala.language.higherKinds
import scala.util.chaining._
import cats.effect.{Async, IO, IOApp, Temporal}
import cats.effect.std.Console
import cats.syntax.all._
import fs2.{Pipe, Stream, INothing, text}
import fs2.concurrent.Topic
import ca.valencik.aoc.AdventApp

case class Bingo private (nums: List[List[Int]], called: List[(Int, Int)]):
  def dab(n: Int): Bingo =
    val r1: Int = nums.indexWhere(row => row.contains(n))
    if (r1 >= 0) {
      val c1: Int = nums(r1).indexOf(n)
      this.copy(called = called.prepended((r1, c1)))
    } else this

  val didWin: Boolean =
    val rowWin: Boolean = called.groupMapReduce(_._1)(_ => 1)(_ + _).valuesIterator.contains(5)
    val colWin: Boolean = called.groupMapReduce(_._2)(_ => 1)(_ + _).valuesIterator.contains(5)
    rowWin || colWin

  def sumOfUncalled: Int =
    val boardSum = nums.map(_.sum).sum
    boardSum - called.map(nums(_)(_)).sum

  lazy val lastDab = called.head.pipe(nums(_)(_))

  def callBingo: Int =
    sumOfUncalled * lastDab

  override def toString: String =
    nums.map(ns => ns.map(n => f"$n%2d").mkString(" ")).mkString("\n")

object Bingo:
  def apply(rows: List[String]): Bingo =
    val nums: List[List[String]] = rows.map(_.split(" +").filterNot(_.isEmpty).toList)
    Bingo(nums.map(r => r.map(_.trim.toInt)), List.empty)

  def empty: Bingo = Bingo(List.empty, List.empty)

class EventService[F[_]](calls: Topic[F, Int], wins: Topic[F, Int])(
    nums: Stream[F, Int],
    boards: Stream[F, Bingo]
)(implicit F: Temporal[F]):

  def startPublisher: Stream[F, Unit] =
    Stream.sleep(0.5.second).drain ++ Stream
      .awakeEvery[F](0.15.second)
      .zipRight(nums)
      .through(calls.publish)

  def startSubscribers: Stream[F, Bingo] =
    def processEvent(b: Bingo, n: Int): F[Bingo] =
      val newB = b.dab(n)
      if (newB.didWin)
        wins
          .publish1(newB.callBingo)
          .map(_ => Bingo.empty)
      else F.pure(newB)

    val events: Stream[F, Int] = calls.subscribe(100)
    boards
      .map(b => events.evalScan(b)(processEvent))
      .parJoinUnbounded

object Day04 extends AdventApp(2021, 4):
  def program[F[_]: Async](in: Stream[F, String]): Stream[F, Int] = for {
    calls <- Stream.eval(Topic[F, Int])
    wins  <- Stream.eval(Topic[F, Int])
    lines   = in.through(text.lines)
    nums    = lines.take(1).flatMap(s => Stream.emits(s.split(",").map(_.toInt)))
    bs      = lines.drop(2).sliding(5, 6).map(c => Bingo(c.toList))
    service = new EventService[F](calls, wins)(nums, bs)
    ps      = service.startPublisher.concurrently(service.startSubscribers)
    res <- ps.drain.mergeHaltL(wins.subscribe(100))
  } yield res

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    program(in).take(1).map(_.toString).compile.toList.map(_.head)
  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    program(in).takeRight(1).map(_.toString).compile.toList.map(_.head)
