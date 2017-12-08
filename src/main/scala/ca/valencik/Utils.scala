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
}
