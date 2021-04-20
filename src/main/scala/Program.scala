import algorithms.{AStarNode, Astar, Bfs, Dfs, Node}
import maze.{Cell, Location, Maze}

object Program extends App {

  val maze = xxx2

  val m = Maze(maze, Location(0, 0), Location(9, 9))
  run(m, Dfs.run)

  println("--------------------------------------")

  val m2 = Maze(maze, Location(0, 0), Location(9, 9))
  run(m2, Bfs.run)

  println("--------------------------------------")

  val m3 = Maze(maze, Location(0, 0), Location(9, 9))
  run2(m3, Astar.run(x => manhattanDistance(x, m.goal)))

  def run(m: Maze, algo: (Location, Location => Boolean, Location => List[Location]) => Option[Node[Location]]): Unit = {
    println(m.show())
    println(algo(m.start, m.checkIfGoalAchieved, m.successors)
      .map(solution => solution.toPath)
      .map(m.show)
      .getOrElse("No solution found using depth-first search!"))
  }

  def run2(m: Maze, algo: (Location, Location => Boolean, Location => List[Location]) => Option[AStarNode[Location]]): Unit = {
    println(m.show())
    println(algo(m.start, m.checkIfGoalAchieved, m.successors)
      .map(solution => solution.toPath)
      .map(m.show)
      .getOrElse("No solution found using depth-first search!"))
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

    val all = lab.split(",").flatMap(Cell.from)
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

    val all = lab.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

  def manhattanDistance(ml: Location, goal: Location): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }

}
