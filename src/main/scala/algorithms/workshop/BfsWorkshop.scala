package algorithms.workshop

import algorithms.Node

import scala.collection.mutable

object BfsWorkshop {
  def run[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Option[Node[T]] = {
    val frontier = mutable.Queue[Node[T]]()
    frontier enqueue Node(initial)

    val explored = mutable.Set[T]()

    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue
      val currentState = currentNode.state
      if (goalTest(currentState)) return Some(currentNode)
      successors(currentState)
        .filter(!explored.contains(_))
        .foreach(child => {
          explored add child
          frontier enqueue Node(child, Some(currentNode))
        })
    }
    None
  }
}
