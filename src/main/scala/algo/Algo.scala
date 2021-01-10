package algo

import maze.Node

import scala.collection.mutable

object Algo {
  def dfs[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
    val frontier: mutable.Stack[Node[T]] = mutable.Stack()
    frontier.push(Node(initial))
    val explored: mutable.Set[T] = mutable.Set[T]()
    while (frontier.nonEmpty) {
      val currentNode = frontier.pop
      val currentState = currentNode.state
      if (goalTest(currentState)) return currentNode
      for (
        child <- successors(currentState)
        if !explored.contains(child)
      ) {
        explored.add(child)
        frontier.push(Node(child, currentNode))
      }
    }
    null
  }

  def bfs[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Node[T] = {
    val frontier: mutable.Queue[Node[T]] = mutable.Queue()
    frontier.enqueue(Node(initial))
    val explored: mutable.Set[T] = mutable.Set[T]()
    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue()
      val currentState = currentNode.state
      if (goalTest(currentState)) return currentNode
      for (
        child <- successors(currentState)
        if !explored.contains(child)
      ) {
        explored.add(child)
        frontier.enqueue(Node(child, currentNode))
      }
    }
    null
  }

  def astar[T](heuristic: T => Double)(
    initial: T,
    goalTest: T => Boolean,
    successors: T => List[T]
  ): Node[T] = {
    val frontier = mutable.PriorityQueue[Node[T]]()
    frontier.enqueue(new Node[T](initial, null, 0.0, heuristic(initial)))
    // explored is where we've been
    val explored = mutable.Map[T, Double]()
    explored.put(initial, 0.0)
    // keep going while there is more to explore
    while (frontier.nonEmpty) {
      val currentNode = frontier.dequeue()
      val currentState = currentNode.state
      // if we found the goal, we're done
      if (goalTest(currentState)) return currentNode
      // check where we can go next and haven't explored
      for (child <- successors(currentState)) { // 1 here assumes a grid, need a cost function for more
        val newCost = currentNode.cost + 1
        if (!explored.contains(child) || explored(child) > newCost) {
          explored.put(child, newCost)
          frontier.enqueue(Node(child, currentNode, newCost, heuristic(child)))
        }
      }
    }
    null // went through everything and never found goal

  }
}
