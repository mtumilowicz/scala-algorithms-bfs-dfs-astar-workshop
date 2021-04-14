package maze

import scala.collection.mutable.ListBuffer

case class Maze(
                 grid: Array[Array[Cell]],
                 start: Location,
                 goal: Location,
               ) {

  val rows: Int = grid.length
  val cols: Int = grid(0).length

  def show(): String =
    grid.map(_.map(_.show()).mkString).mkString(System.lineSeparator)

  def mark(path: List[Location]): Unit = {
    path.foreach { step => grid(step.row)(step.column) = Cell.Path() }
    grid(start.row)(start.column) = Cell.Start()
    grid(goal.row)(goal.column) = Cell.Goal()
  }

  def goalTest(ml: Location): Boolean = goal == ml

  def successors(ml: Location): List[Location] = {
    val locations = ListBuffer[Location]()
    if (ml.row + 1 < rows && (grid(ml.row + 1)(ml.column) != Cell.Blocked())) locations.addOne(Location(ml.row + 1, ml.column))
    if (ml.row - 1 >= 0 && (grid(ml.row - 1)(ml.column) != Cell.Blocked())) locations.addOne(Location(ml.row - 1, ml.column))
    if (ml.column + 1 < cols && (grid(ml.row)(ml.column + 1) != Cell.Blocked())) locations.addOne(Location(ml.row, ml.column + 1))
    if (ml.column - 1 >= 0 && (grid(ml.row)(ml.column - 1) != Cell.Blocked())) locations.addOne(Location(ml.row, ml.column - 1))
    locations.toList
  }

  def manhattanDistance(ml: Location): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }
}

object Maze {
  def apply(): Maze = {
    Maze(
      generateRandomGrid(10, 10, 0.2),
      start = Location(0, 0),
      goal = Location(9, 9),
    )
  }

  private def generateRandomGrid(rows: Int, cols: Int, sparseness: Double): Array[Array[Cell]] = {
    val r = scala.util.Random
    val grid: Array[Array[Cell]] = Array.fill(rows, cols)(Cell.Empty())
    for (
      row <- 0 until rows;
      col <- 0 until cols
    ) if (r.nextDouble() < sparseness) grid(row)(col) = Cell.Blocked()

    grid
  }
}
