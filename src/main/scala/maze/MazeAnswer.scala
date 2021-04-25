package maze

case class MazeAnswer(
                 grid: Array[Array[Cell]],
                 start: Location,
                 goal: Location,
               ) {

  val rows: Int = grid.length
  val cols: Int = grid(0).length
  val directions = List((1, 0), (-1, 0), (0, 1), (0, -1))

  def checkIfGoalAchieved(location: Location): Boolean = goal == location

  def successors(location: Location): List[Location] = {
    val position = (location.row, location.column)
    directions
      .map { direction => goInDirection(position, direction) }
      .filter(isInRange)
      .filter(isNotBlocked)
      .map { case (newR, newC) => Location(newR, newC) }
  }

  def show(path: List[Location] = List()): String = {
    val copy = mark(path)
    val prepareRows = copy.map(_.map(_.show()).mkString(","))
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

  private def mark(path: List[Location]): Array[Array[Cell]] = {
    val copy = grid.map(_.map(identity))
    path.foreach { step => copy(step.row)(step.column) = Cell.Path() }
    copy(start.row)(start.column) = Cell.Start()
    copy(goal.row)(goal.column) = Cell.Goal()
    copy
  }
}
