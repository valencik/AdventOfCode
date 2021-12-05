package ca.valencik.aoc.y2021

import scala.language.higherKinds
import scala.util.chaining._
import cats.effect.{Async, IO, IOApp}
import cats.syntax.all._
import fs2.{Pipe, Stream, INothing, text}
import fs2.concurrent.Topic
import ca.valencik.aoc.AdventApp
import cats.Applicative

object Day04b extends AdventApp(2021, 4):

  def boardStream[F[_]: Async](init: Bingo)(winT: Topic[F, (Int, Int)])(implicit
      F: Applicative[F]
  ): Pipe[F, Int, INothing] =
    _.evalScan(init)((b, n) =>
      b.dab(n) match
        case nb if (nb.didWin) => winT.publish1(nb.callBingo).as(nb.empty)
        case nb                => F.pure(nb)
    ).drain

  def program[F[_]: Async](in: Stream[F, String]): F[Stream[F, (Int, Int)]] = for {
    calls <- Topic[F, Int]
    wins  <- Topic[F, (Int, Int)]
    lines <- in.through(text.lines).compile.toVector
    nums  = Stream.emits(lines.take(1).flatMap(s => s.split(",").map(_.toInt)))
    bs    = lines.drop(2).sliding(5, 6).map(c => Bingo(c.toList)).toList
    pipes = bs.map(b => boardStream(b)(wins))
    cast  = nums.broadcastThrough(pipes: _*)
    res   = wins.subscribe(1).mergeHaltR(cast)
  } yield res

  def rankAllGames[F[_]: Async](wins: Stream[F, (Int, Int)])(strat: Ordering[Int]): F[String] =
    wins.compile.toList.map(_.sortBy(_._1)(strat).head.pipe(_._2.toString))

  override def part1[F[_]: Async](in: Stream[F, String]): F[String] =
    program(in).flatMap(rankAllGames(_)(implicitly[Ordering[Int]]))
  override def part2[F[_]: Async](in: Stream[F, String]): F[String] =
    program(in).flatMap(rankAllGames(_)(implicitly[Ordering[Int]].reverse))
