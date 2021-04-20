package pt

import algorithms.Bfs

object YYY extends App {

  val MAX_NUM = 3

  val start = PtState(MAX_NUM, MAX_NUM, true)
  val solution = Bfs.run(start, start.goalTest, start.successors)
  Bfs.run(start, start.goalTest, start.successors) match {
    case Some(solution) => PtState.displaySolution(solution.toPath)
    case None => System.out.println("No solution found!")
  }
}
