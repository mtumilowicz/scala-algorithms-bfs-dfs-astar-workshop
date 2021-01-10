object Intro extends App {
  val m = new Maze()
  println(m.show())
  val solution1 = Algo.dfs(m.start, m.goalTest, m.successors)
  if (solution1 == null) System.out.println("No solution found using depth-first search!")
  else {
    val path1 = solution1.toPath()
    m.mark(path1)
    println(m.show())
  }
}
