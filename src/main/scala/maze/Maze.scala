package maze

case class Maze(
                 grid: Array[Array[Cell]],
                 start: Location,
                 goal: Location,
               ) {

  val rows: Int = grid.length
  val cols: Int = grid(0).length
  val directions = List((1, 0), (-1, 0), (0, 1), (0, -1))

  def checkIfGoalAchieved(ml: Location): Boolean = goal == ml

  def successors(ml: Location): List[Location] = {
    val position = (ml.row, ml.column)
    directions
      .map { direction => goInDirection(position, direction) }
      .filter(isInRange)
      .filter(isNotBlocked)
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

  private def isNotBlocked(position: (Int, Int)): Boolean = {
    val (row, col) = position
    grid(row)(col) != Cell.Blocked()
  }

  private def isInRange(coordinates: (Int, Int)): Boolean = {
    val (row, col) = coordinates
    0 <= row && row < rows && 0 <= col && col < cols
  }

  private def goInDirection(position: (Int, Int), direction: (Int, Int)): (Int, Int) = {
    val (rowDirection, colDirection) = direction
    val (row, col) = position
    (row + rowDirection, col + colDirection)
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
