import scala.collection.mutable.ListBuffer

case class Maze(
                 rows: Int,
                 columns: Int,
                 start: MazeLocation,
                 goal: MazeLocation,
                 sparseness: Double
               ) {

  val grid: Array[Array[Cell]] = generateRandomGrid(0.2)

  def this() {
    this(10, 10, MazeLocation(0, 0), MazeLocation(9, 9), 0.2)
  }

  private def generateRandomGrid(sparseness: Double): Array[Array[Cell]] = {
    val r = scala.util.Random
    val grid: Array[Array[Cell]] = Array.fill(rows, columns)(Empty())
    for (
      row <- 0 until rows;
      col <- 0 until columns
    ) if (r.nextDouble() < sparseness) grid(row)(col) = Blocked()

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
      grid(ml.row)(ml.column) = Path()
    }

    grid(start.row)(start.column) = Start()
    grid(goal.row)(goal.column) = Goal()
  }

  def goalTest(ml: MazeLocation): Boolean = goal.equals(ml)

  def successors(ml: MazeLocation): List[MazeLocation] = {
    val locations = ListBuffer[MazeLocation]()
    if (ml.row + 1 < rows && (grid(ml.row + 1)(ml.column) != Blocked())) locations.addOne(MazeLocation(ml.row + 1, ml.column))
    if (ml.row - 1 >= 0 && (grid(ml.row - 1)(ml.column) != Blocked())) locations.addOne(MazeLocation(ml.row - 1, ml.column))
    if (ml.column + 1 < columns && (grid(ml.row)(ml.column + 1) != Blocked())) locations.addOne(MazeLocation(ml.row, ml.column + 1))
    if (ml.column - 1 >= 0 && (grid(ml.row)(ml.column - 1) != Blocked())) locations.addOne(MazeLocation(ml.row, ml.column - 1))
    locations.toList
  }
}
