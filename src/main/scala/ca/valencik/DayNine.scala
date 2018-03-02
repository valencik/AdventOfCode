package ca.valencik

import ca.valencik.Utils.putStrLn

object DayNine extends App {

  case class State(score: Int,
                   depth: Int,
                   garbage: Boolean,
                   ignoreNext: Boolean) {
    def incrDepth: State    = State(score, depth + 1, garbage, ignoreNext)
    def incrScore: State    = State(score + depth, depth - 1, garbage, ignoreNext)
    def startIgnore: State  = State(score, depth, garbage, true)
    def endIgnore: State    = State(score, depth, garbage, false)
    def startGarbage: State = State(score, depth, true, ignoreNext)
    def endGarbage: State   = State(score, depth, false, ignoreNext)
  }
  case object State {
    // scalastyle:off cyclomatic.complexity
    def parse(s: State, c: Char): State = {
      if (s.ignoreNext) s.endIgnore
      else
        c match {
          case '!' => s.startIgnore
          case '<' => if (s.garbage) s else s.startGarbage
          case '>' => s.endGarbage
          case '{' => if (s.garbage) s else s.incrDepth
          case '}' => if (s.garbage) s else s.incrScore
          case _   => s
        }
    }
    // scalastyle:on cyclomatic.complexity

    val empty                        = State(0, 0, false, false)
    def process(text: String): State = text.foldLeft(State.empty)(State.parse)
  }

  val inputText = args.mkString("")
  putStrLn(
    State.process(inputText).toString
  )
}
