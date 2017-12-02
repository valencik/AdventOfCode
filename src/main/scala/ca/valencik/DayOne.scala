package ca.valencik

object DayOne extends App {
  def partOne(numString: String): Int = {
    val listChars = numString.toList :+ numString.toList.head
    listChars
      .sliding(2)
      .map {
        case x :: y :: Nil => if (x == y) x.asDigit else 0
        case _  => 0
      }
      .sum
  }

  def partTwo(numString: String): Int = {
    val listChars = numString.toList
    val size = listChars.length
    def jumphalf(n: Int) = listChars((n + size / 2) % size)
    listChars
      .zipWithIndex
      .map {
        case (x, i)  => if (x == jumphalf(i)) x.asDigit else 0
      }
      .sum
  }

  println("InverseCaptcha partOne: " + partOne(args(0)))
  println("InverseCaptcha partTwo: " + partTwo(args(0)))
}
