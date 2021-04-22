package algorithms

import scala.collection.mutable

object Astar {
  def run[T](heuristic: T => Double)
              (initial: T, goalTest: T => Boolean, successors: T => List[T]): Option[AStarNode[T]] = {
    val frontier = mutable.PriorityQueue[AStarNode[T]]()(Ordering[AStarNode[T]].reverse)
    frontier.enqueue(AStarNode[T](initial, null, 0.0, heuristic(initial)))
    val explored = mutable.Map[T, Double]()
    explored.put(initial, 0.0)
    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue()
      val currentState = currentNode.state
      if (goalTest(currentState)) return Some(currentNode)
      for (child <- successors(currentState)) {
        val newCost = currentNode.cost + 1
        if (!explored.contains(child) || explored(child) > newCost) {
          explored put (child, newCost)
          frontier enqueue AStarNode(child, currentNode, newCost, heuristic(child))
        }
      }
    }
    None
  }
}
