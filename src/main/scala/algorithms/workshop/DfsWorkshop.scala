package algorithms.workshop

import algorithms.Node

import scala.collection.mutable

object DfsWorkshop {
  def run[T](initial: T, goalTest: T => Boolean, successors: T => List[T]): Option[Node[T]] = {
    // frontier as a stack, hint: mutable.Stack
    // push initial node
    // explored as a set, hint: mutable.Set

    // until frontier not empty, hint: nonEmpty
    // pop current node
    // get its state
    // test if goal, if yes - return
    // get successors, filter not explored
    // add to explored and push to frontier
    None
  }
}
