package algorithms.workshop

import algorithms.AStarNode

import scala.collection.mutable

object AstarWorkshop {
  def run[T](heuristic: T => Double)
            (initial: T, goalTest: T => Boolean, successors: T => List[T]): Option[AStarNode[T]] = {
    // frontier = priority queue of node (reverse it), hint mutable.PriorityQueue, AStarNode, reverse
    // enqueue initial, hint: cost = 0, heuristic = calculate it
    // explored = map of T and cost, hint = mutable.Map
    // put initial
    // while there is any in frontier, hint: nonEmpty
    // dequeue best
    // get state
    // test if goal, if yes - return
    // process all successor, hint: successors(currentState)
    // count newCost in the simplest way: previous cost + 1
    // if is not explored yet or newCost is better that already counted cost
    // put in explored with newCost
    // enqueue in frontier
    None
  }
}
