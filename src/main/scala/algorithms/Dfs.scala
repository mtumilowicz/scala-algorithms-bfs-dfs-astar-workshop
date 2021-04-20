package algorithms

import scala.collection.mutable

object Dfs {
  def run[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
    val frontier = mutable.Stack[Node[T]]()
    frontier push Node(initial)

    val explored = mutable.Set[T]()

    while (frontier.nonEmpty) {
      val currentNode = frontier.pop
      val currentState = currentNode.state
      if (goalTest(currentState)) return currentNode
      successors(currentState)
        .filter(!explored.contains(_))
        .foreach(child => {
          explored add child
          frontier push Node(child, Some(currentNode))
        })
    }
    null
  }
}
