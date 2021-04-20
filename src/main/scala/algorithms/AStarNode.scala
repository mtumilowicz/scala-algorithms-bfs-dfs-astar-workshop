package algorithms

import scala.collection.mutable.ListBuffer

case class AStarNode[T](
                         state: T,
                         parent: AStarNode[T] = null,
                         cost: Double = 0.0,
                         heuristic: Double = 0.0
                       ) extends Comparable[AStarNode[T]] {
  override def compareTo(other: AStarNode[T]): Int = {
    val mine = cost + heuristic
    val theirs = other.cost + other.heuristic
    mine.compareTo(theirs)
  }

  def toPath(): List[T] = {
    val path = ListBuffer[T]()
    path.addOne(state)
    var node = this
    while (node.parent != null) {
      node = node.parent
      path.addOne(node.state)
    }

    path.reverse.toList
  }
}
