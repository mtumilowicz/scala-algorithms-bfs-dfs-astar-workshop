package pt

import algorithms.Bfs

object YYY extends App {

  val MAX_NUM = 3

  val start = PtState(MAX_NUM, MAX_NUM, true)
  val solution = Bfs.run(start, start.goalTest, start.successors)
  if (solution == null) System.out.println("No solution found!")
  else {
    val path = solution.toPath
    PtState.displaySolution(path)
  }

}
