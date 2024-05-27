[![Build Status](https://app.travis-ci.com/mtumilowicz/scala-algorithms-bfs-dfs-astar-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/scala-algorithms-bfs-dfs-astar-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

# scala-algorithms-bfs-dfs-astar-workshop
* references
    * https://www.manning.com/books/classic-computer-science-problems-in-java
    * https://medium.com/@nicholas.w.swift/easy-a-star-pathfinding-7e6689c7f7b2

## preface
* goal of this workshop:
    * basic understanding of bfs, dfs and astar algorithms
    * the ability to apply theory in practice

## dfs
```
val frontier = stack
frontier push initialNode

val explored = set

while (frontier.nonEmpty) {
  val current = frontier.pop
  if (current is goal) return Some(current)
  successors(current)
    .filter(not in explored)
    .foreach(child => {
      explored add child
      frontier push child
    })
}
None
```
note also that you have some structure to track path
```
case class Node[T](state: T, parent: Option[Node[T]] = Option.empty)
```

## bfs
* just take dfs and use queue instead of stack
* always finds the shortest solution path where one exists
    * why?
        * try to find path from Cracow to Warsaw with fewest stops (by train)
        * dfs: keep going in the same direction and backtrack when a dead end
            * route could go through Poznan
        * bfs: check all of the stations one stop away from Cracow
            * then, two stops away from Cracow and so on
            * when you do find Warsaw, you will know you have found the route with the fewest stops
                * all of the stations that are fewer stops away from Cracow were checked

## astar
* one important aspect of A* is `f = g + h`
    * G - distance between the current node and the start node
    * H - heuristic (estimated distance from the current node to the end node)
    * F - total cost of the node
        * we can look at all our nodes and ask: "is this the best node I can pick to move
        forward?"
    * and slightly modified Node class
        ```
        case class AStarNode[T](
                                 state: T,
                                 parent: AStarNode[T] = null,
                                 cost: Double = 0.0,
                                 heuristic: Double = 0.0
                               )
        ```
* code
    ```
    val frontier = priorityQueue // by total cost
    frontier enqueue initial
    val explored = map // state, cost
    explored put (initial, 0.0)
    while (frontier.nonEmpty) {
      val current = frontier.dequeue()
      if (current is goal) return Some(current)
      for (child <- successors(current)) {
        val newCost = currentNode.cost + 1
        if (child is not explored || explored(child) > newCost) {
          explored put (child, newCost)
          frontier enqueue child
        }
      }
    }
    None
    ```
    * `explored(child) > newCost`
        * check if path to that square is better, using G cost as the measure
        * a lower G cost means that this is a better path

## practice
* task 1
    * you have a maze (n x m) with start location (startX, startY), goal location (goalX, goalY) and
    possible walls
    * find path from start to goal
        * note that the path could not exist or there could be many solutions
    * example
        ```
        "S, , , ,X,X,X,X,X,X,"
        "X,X,X, ,X,X,X,X,X,X,"
        "X,X,X, ,X,X,X,X,X,X,"
        " , , , ,X,X,X,X,X,X,"
        " ,X,X,X,X,X,X,X,X,X,"
        " ,X,X,X,X,X,X,X,X,X,"
        " ,X,X,X,X,X,X,X,X,X,"
        " , , , , ,X,X,X,X,X,"
        "X,X,X,X, , , , , , ,"
        "X,X,X,X,X,X,G, , , ,"
        ```
        and the solution
        ```
        "S,*,*,*,X,X,X,X,X,X"
        "X,X,X,*,X,X,X,X,X,X"
        "X,X,X,*,X,X,X,X,X,X"
        "*,*,*,*,X,X,X,X,X,X"
        "*,X,X,X,X,X,X,X,X,X"
        "*,X,X,X,X,X,X,X,X,X"
        "*,X,X,X,X,X,X,X,X,X"
        "*,*,*,*,*,X,X,X,X,X"
        "X,X,X,X,*,*,*, , , "
        "X,X,X,X,X,X,G, , , "
        ```
* task 2
    * three policemen and three thieves are on the west bank of a river
    * they all must cross to the opposite bank of the river
    * they have a riverboat that can hold two people
    * there may never be more thieves than policemen
    * riverboat must have at least one person on board to cross the river
    * return the sequence of crossings
