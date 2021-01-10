package maze

import algo.Algo

object Intro extends App {

  val m = new Maze()
  run(m, Algo.dfs)

  println("--------------------------------------")

  val m2 = new Maze()
  run(m2, Algo.bfs)

  println("--------------------------------------")

  val m3 = new Maze()
  run(m3, Algo.astar(m3.manhattanDistance))

  def run(m: Maze, algo: (MazeLocation, MazeLocation => Boolean, MazeLocation => List[MazeLocation]) => Node[MazeLocation]): Unit = {
    println(m.show())
    val solution1 = algo(m.start, m.goalTest, m.successors)
    if (solution1 == null) System.out.println("No solution found using depth-first search!")
    else {
      val path1 = solution1.toPath()
      m.mark(path1)
      println(m.show())
    }
  }

}
