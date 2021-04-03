package algorithms

import maze.Node

import scala.collection.mutable

object Algorithms {
  def dfs[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
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
          frontier push Node(child, currentNode)
        })
    }
    null
  }

  def bfs[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
    val frontier = mutable.Queue[Node[T]]()
    frontier enqueue Node(initial)

    val explored = mutable.Set[T]()

    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue
      val currentState = currentNode.state
      if (goalTest(currentState)) return currentNode
      successors(currentState)
        .filter(!explored.contains(_))
        .foreach(child => {
          explored add child
          frontier enqueue Node(child, currentNode)
        })
    }
    null
  }

  def astar[T](heuristic: T => Double)
              (initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
    val frontier = mutable.PriorityQueue[Node[T]]()
    frontier.enqueue(new Node[T](initial, null, 0.0, heuristic(initial)))
    val explored = mutable.Map[T, Double]()
    explored.put(initial, 0.0)
    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue()
      val currentState = currentNode.state
      if (goalTest(currentState)) return currentNode
      for (child <- successors(currentState)) {
        val newCost = currentNode.cost + 1
        if (!explored.contains(child) || explored(child) > newCost) {
          explored put (child, newCost)
          frontier enqueue Node(child, currentNode, newCost, heuristic(child))
        }
      }
    }
    null
  }
}
