package ca.valencik

import ca.valencik.Utils.putStrLn

object StreamProcessing extends App {

  case class State(score: Int,
                   depth: Int,
                   garbageCount: Int,
                   garbage: Boolean,
                   ignoreNext: Boolean) {
    def incrDepth: State    = this.copy(depth = depth + 1)
    def incrScore: State    = this.copy(score = score + depth, depth = depth - 1)
    def startIgnore: State  = this.copy(ignoreNext = true)
    def endIgnore: State    = this.copy(ignoreNext = false)
    def startGarbage: State = this.copy(garbage = true)
    def endGarbage: State   = this.copy(garbage = false)
    def incrGarbage: State  = this.copy(garbageCount = garbageCount + 1)
  }
  case object State {
    // scalastyle:off cyclomatic.complexity
    def parse(s: State, c: Char): State = {
      if (s.ignoreNext) s.endIgnore
      else
        c match {
          case '!' => s.startIgnore
          case '<' => if (s.garbage) s.incrGarbage else s.startGarbage
          case '>' => s.endGarbage
          case '{' => if (s.garbage) s.incrGarbage else s.incrDepth
          case '}' => if (s.garbage) s.incrGarbage else s.incrScore
          case _   => if (s.garbage) s.incrGarbage else s
        }
    } // scalastyle:on cyclomatic.complexity
    val empty                        = State(0, 0, 0, false, false)
    def process(text: String): State = text.foldLeft(State.empty)(State.parse)
  }

  putStrLn(State.process(args.mkString).toString)
}
