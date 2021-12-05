package ca.valencik.aoc

import cats.effect.{Async, IOApp, IO}
import fs2.{Stream, text}
import fs2.io.file.{Files, Path}

abstract class AdventApp(year: Int, day: Int) extends IOApp.Simple:
  def part1[F[_]: Async](input: Stream[F, String]): F[String]
  def part2[F[_]: Async](input: Stream[F, String]): F[String]

  val run =
    val fileString = f"/y$year/Day$day%02d.txt"
    val filePath   = getClass.getResource(fileString).getPath
    val data       = Files[IO].readAll(Path(filePath)).through(text.utf8.decode)

    part1[IO](data).flatMap(x => IO.println(s"Part one: $x")) *>
      part2[IO](data).flatMap(x => IO.println(s"Part two: $x"))
