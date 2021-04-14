package maze

import algorithms.Algorithms

object Intro extends App {

  val m = Maze()
  run(m, Algorithms.dfs)

  println("--------------------------------------")

  val m2 = Maze()
  run(m2, Algorithms.bfs)

  println("--------------------------------------")

  val m3 = Maze()
  run(m3, Algorithms.astar(x => manhattanDistance(x, m3.goal)))

  def run(m: Maze, algo: (Location, Location => Boolean, Location => List[Location]) => Node[Location]): Unit = {
    println(m.show())
    val solution1 = algo(m.start, m.goalTest, m.successors)
    if (solution1 == null) System.out.println("No solution found using depth-first search!")
    else {
      val path1 = solution1.toPath()
      m.mark(path1)
      println(m.show())
    }
  }

  def manhattanDistance(ml: Location, goal: Location): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }

}
