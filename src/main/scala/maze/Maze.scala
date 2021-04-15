package maze

import scala.collection.mutable.ListBuffer

case class Maze(
                 grid: Array[Array[Cell]],
                 start: Location,
                 goal: Location,
               ) {

  val rows: Int = grid.length
  val cols: Int = grid(0).length
  val directions = Array((1, 0), (-1, 0), (0, 1), (0, -1))

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
    val (row, col) = (ml.row, ml.column)
    if (row + 1 < rows && (grid(row + 1)(col) != Cell.Blocked()))
      locations += Location(row + 1, col)
    if (row - 1 >= 0 && (grid(row - 1)(col) != Cell.Blocked())) {
      locations += Location(row - 1, col)
    }
    if (col + 1 < cols && (grid(row)(col + 1) != Cell.Blocked())) {
      locations += Location(ml.row, ml.column + 1)
    }
    if (col - 1 >= 0 && (grid(row)(col - 1) != Cell.Blocked())) {
      locations += Location(row, col - 1)
    }
    locations.toList
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
