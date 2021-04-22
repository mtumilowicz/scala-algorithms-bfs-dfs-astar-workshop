package pt

import algorithms.Bfs

object YYY extends App {

  val start = LeftBankState(3, 3, true)
  val solution = Bfs.run(start, start.checkIfGoalAchieved, (x: LeftBankState) => x.successors())
  solution match {
    case Some(solution) => LeftBankState.displaySolution(solution.toPath)
    case None => println("No solution found!")
  }
}
