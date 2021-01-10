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
}
