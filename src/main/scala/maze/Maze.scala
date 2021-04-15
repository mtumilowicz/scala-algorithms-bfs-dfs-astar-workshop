package maze

case class Maze(
                 grid: Array[Array[Cell]],
                 start: Location,
                 goal: Location,
               ) {

  val rows: Int = grid.length
  val cols: Int = grid(0).length
  val directions = List((1, 0), (-1, 0), (0, 1), (0, -1))

  def goalTest(ml: Location): Boolean = goal == ml

  def successors(ml: Location): List[Location] = {
    val (row, col) = (ml.row, ml.column)
    directions
      .map { case (rowDirection, colDirection) => (row + rowDirection, col + colDirection) }
      .filter(isInRange)
      .filter { case (newR, newC) => grid(newR)(newC) != Cell.Blocked() }
      .map { case (newR, newC) => Location(newR, newC) }
  }

  def mark(path: List[Location]): Unit = {
    path.foreach { step => grid(step.row)(step.column) = Cell.Path() }
    grid(start.row)(start.column) = Cell.Start()
    grid(goal.row)(goal.column) = Cell.Goal()
  }

  def show(): String = {
    val prepareRows = grid.map(_.map(_.show()).mkString)
    prepareRows.mkString(System.lineSeparator)
  }

  private def isInRange(coordinates: (Int, Int)): Boolean = {
    val (row, col) = coordinates
    0 <= row && row < rows && 0 <= col && col < cols
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
