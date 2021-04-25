package algorithms.workshop

import algorithms.Node

import scala.collection.mutable

object BfsWorkshop {
  def run[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Option[Node[T]] = {
    // frontier as a queue, hint: mutable.Queue
    // put initial node
    // explored as a set, hint: mutable.Set

    // until frontier not empty, hint: nonEmpty
    // dequeue current node
    // get its state
    // test if goal, if yes - return
    // get successors, filter not explored
    // add to explored and enqueue in frontier
    None
  }
}
