package maze

import algorithms.Algorithms

object Intro extends App {

  val m = Maze(xxx2, Location(0, 0), Location(9, 9))
  run(m, Algorithms.dfs)

  println("--------------------------------------")

  val m2 = Maze(xxx2, Location(0, 0), Location(9, 9))
  run(m2, Algorithms.bfs)

  println("--------------------------------------")

  val m3 = Maze(xxx2, Location(0, 0), Location(9, 9))
  run(m3, Algorithms.astar(x => manhattanDistance(x, m.goal)))

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

  def xxx: Array[Array[Cell]] = {
    val lab =
        "S, , , ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        " , , , ,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " , , , , ,X,X,X,X,X," +
        "X,X,X,X, , , , , , ," +
        "X,X,X,X,X,X,G, , , ,"

    val all = lab.split(",").map(Cell.from).filter(_ != null)
    all.grouped(10).toArray
  }

  def xxx2: Array[Array[Cell]] = {
    val lab =
        "S, , , , , , , , , ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " , , , , , , , , ,G,"

    val all = lab.split(",").map(Cell.from).filter(_ != null)
    all.grouped(10).toArray
  }

  def manhattanDistance(ml: Location, goal: Location): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }

}
