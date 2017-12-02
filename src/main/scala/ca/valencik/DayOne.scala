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

  println("InverseCaptcha partOne: " + partOne(args(0)))
}
