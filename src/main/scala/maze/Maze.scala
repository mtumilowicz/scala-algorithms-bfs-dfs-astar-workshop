package maze

import scala.collection.mutable.ListBuffer

case class Maze(
                 rows: Int,
                 columns: Int,
                 start: MazeLocation,
                 goal: MazeLocation,
                 sparseness: Double
               ) {

  val grid: Array[Array[Cell]] = generateRandomGrid(0.2)

  private def generateRandomGrid(sparseness: Double): Array[Array[Cell]] = {
    val r = scala.util.Random
    val grid: Array[Array[Cell]] = Array.fill(rows, columns)(Cell.Empty())
    for (
      row <- 0 until rows;
      col <- 0 until columns
    ) if (r.nextDouble() < sparseness) grid(row)(col) = Cell.Blocked()

    grid
  }

  def show(): String = {
    val sb = new StringBuilder()
    for (row <- grid) {
      for (cell <- row) {
        sb.append(cell.show())
      }
      sb.append(System.lineSeparator)
    }

    sb.toString()
  }

  def mark(path: List[MazeLocation]): Unit = {
    for (ml <- path) {
      grid(ml.row)(ml.column) = Cell.Path()
    }

    grid(start.row)(start.column) = Cell.Start()
    grid(goal.row)(goal.column) = Cell.Goal()
  }

  def goalTest(ml: MazeLocation): Boolean = goal.equals(ml)

  def successors(ml: MazeLocation): List[MazeLocation] = {
    val locations = ListBuffer[MazeLocation]()
    if (ml.row + 1 < rows && (grid(ml.row + 1)(ml.column) != Cell.Blocked())) locations.addOne(MazeLocation(ml.row + 1, ml.column))
    if (ml.row - 1 >= 0 && (grid(ml.row - 1)(ml.column) != Cell.Blocked())) locations.addOne(MazeLocation(ml.row - 1, ml.column))
    if (ml.column + 1 < columns && (grid(ml.row)(ml.column + 1) != Cell.Blocked())) locations.addOne(MazeLocation(ml.row, ml.column + 1))
    if (ml.column - 1 >= 0 && (grid(ml.row)(ml.column - 1) != Cell.Blocked())) locations.addOne(MazeLocation(ml.row, ml.column - 1))
    locations.toList
  }

  def manhattanDistance(ml: MazeLocation): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }
}

object Maze {
  def apply(): Maze = {
    Maze(
      rows = 10,
      columns = 10,
      start = MazeLocation(0, 0),
      goal = MazeLocation(9, 9),
      sparseness = 0.2
    )
  }
}
