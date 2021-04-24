# scala-algorithms-bfs-dfs-astar-workshop
* references
    * https://www.manning.com/books/classic-computer-science-problems-in-java

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
        * find path from Cracow to Warsaw with fewest stops (by train)
        * dfs: keep going in the same direction and backtrack when a dead end
            * route could go through Poznan
        * bfs: check all of the stations one stop away from Cracow
            * then, two stops away from Cracow and so on
            * when you do find Warsaw, you will know you have found the route with the fewest stops
                * all of the stations that are fewer stops away from Cracow were checked

## astar

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