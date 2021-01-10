object Intro extends App {
  run(Algo.dfs)

  println("--------------------------------------")

  run(Algo.bfs)

  def run(algo: (MazeLocation, MazeLocation => Boolean, MazeLocation => List[MazeLocation]) => Node[MazeLocation]): Unit = {
    val m = new Maze()
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
