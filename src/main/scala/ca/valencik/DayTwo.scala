package ca.valencik

import ca.valencik.Utils.putStrLn

object DayTwo extends App {
  def partOne(numString: String): Int = {
    val rows = numString.lines.toList.map(_.trim).filter(_.nonEmpty)
    rows
      .map(row => {
        val numRow = row.split("\\s+").map(_.toInt)
        numRow.max - numRow.min
      })
      .sum
  }

  def partTwo(numString: String): Int = {
    val rows = numString.lines.toList.map(_.trim).filter(_.nonEmpty)
    rows
      .map(row => {
        val numRow = row.split("\\s+").map(_.toInt)
        (for {
          i <- 0 to numRow.length - 1
          j <- 0 to numRow.length - 1
          if i != j
        } yield (i, j)).map {
          case (i, j) =>
            if (numRow(i) % numRow(j) == 0) numRow(i) / numRow(j) else 0
        }.sum
      })
      .sum
  }

  putStrLn("partOne: " + partOne(args(0)))
  putStrLn("partTwo: " + partTwo(args(0)))
}
