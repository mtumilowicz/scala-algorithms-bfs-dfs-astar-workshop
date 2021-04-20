package algorithms

import scala.annotation.tailrec

case class Node[T](
                    state: T,
                    parent: Option[Node[T]] = Option.empty) {

  def toPath: List[T] = {
    @tailrec
    def inner(current: Node[T] = this, acc: List[T] = List()): List[T] = {
      if (current == null) return acc
      current match {
        case Node(state, None) => state :: acc
        case Node(state, Some(parent)) => inner(parent, state :: acc)
      }
    }

    inner()
  }
}
