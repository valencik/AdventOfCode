package ca.valencik

object Utils {
  def putStrLn(line: String): Unit = {
    // scalastyle:off
    println(line)
    // scalastyle:on
  }

  def perfTime[R](block: => R): R = {
    val t0     = System.nanoTime()
    val result = block // call-by-name
    val t1     = System.nanoTime()
    putStrLn("Elapsed time: " + (t1 - t0) + "ns")
    result
  }

  def infiniteStream[A](xs: Iterable[A]): Stream[A] = {
    lazy val ss: Stream[A] = xs.toStream #::: ss
    ss
  }

  def firstDuplicate[A](xs: Iterable[A]): A = {
    var these: Iterable[A] = xs
    var seen: Set[A]       = Set()
    while (!seen(these.head)) {
      seen += these.head
      these = these.tail
    }
    these.head
  }

}
