package maze.answer

import algorithms.answer.AstarAnswer
import maze.{Cell, Location, Maze}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class MazeAstarAnswerTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("solve the maze") {
    Scenario("unsolvable maze") {
      Given("zero route")
      val maze = Maze(zeroRoutes, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = AstarAnswer.run(location => manhattanDistance(location, maze.goal))(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is empty")
      solution shouldBe None
    }

    Scenario("solvable maze with one route") {
      Given("one route")
      val maze = Maze(oneRoute, Location(0, 0), Location(9, 6))

      When("solve")
      val solution = AstarAnswer.run(location => manhattanDistance(location, maze.goal))(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).map(maze.show).orNull shouldBe oneRouteSolution
    }

    Scenario("solvable maze with two routes") {
      Given("two routes")
      val maze = Maze(twoRoutes, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = AstarAnswer.run(location => manhattanDistance(location, maze.goal))(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).map(maze.show).orNull shouldBe twoRoutesSolution
    }

    Scenario("solvable maze with many routes") {
      Given("multiple routes")
      val maze = Maze(manyRoutes, Location(2, 3), Location(6, 6))

      When("solve")
      val solution = AstarAnswer.run(location => manhattanDistance(location, maze.goal))(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).map(maze.show).orNull shouldBe manyRoutesSolution
    }
  }

  def manyRoutes: Array[Array[Cell]] = {
    val maze =
        " , , , , , , , , , ," +
        " , , , , , , , , , ," +
        " , , ,S, , , , , , ," +
        " , , , , , , , , , ," +
        " , , , , , , , , , ," +
        " , , , , , , , , , ," +
        " , , , , , ,G, , , ," +
        " , , , , , , , , , ," +
        " , , , , , , , , , ," +
        " , , , , , , , , , ,"

    convertToArray(maze)
  }

  def manyRoutesSolution: String =
      " , , , , , , , , , " + System.lineSeparator() +
      " , , , , , , , , , " + System.lineSeparator() +
      " , , ,S, , , , , , " + System.lineSeparator() +
      " , , ,*, , , , , , " + System.lineSeparator() +
      " , , ,*,*, , , , , " + System.lineSeparator() +
      " , , , ,*,*, , , , " + System.lineSeparator() +
      " , , , , ,*,G, , , " + System.lineSeparator() +
      " , , , , , , , , , " + System.lineSeparator() +
      " , , , , , , , , , " + System.lineSeparator() +
      " , , , , , , , , , "

  def twoRoutes: Array[Array[Cell]] = {
    val maze =
        "S, , , , , , , , , ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " ,X,X,X,X,X,X,X,X, ," +
        " , , , , , , , , ,G,"

    convertToArray(maze)
  }

  def twoRoutesSolution: String =
      "S, , , , , , , , , " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X, " + System.lineSeparator() +
      "*,*,*,*,*,*,*,*,*,G"

  def oneRoute: Array[Array[Cell]] = {
    val maze =
        "S, , , ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        " , , , ,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " , , , , ,X,X,X,X,X," +
        "X,X,X,X, , , , , , ," +
        "X,X,X,X,X,X,G, , , ,"

    convertToArray(maze)
  }

  def oneRouteSolution: String =
      "S,*,*,*,X,X,X,X,X,X" + System.lineSeparator() +
      "X,X,X,*,X,X,X,X,X,X" + System.lineSeparator() +
      "X,X,X,*,X,X,X,X,X,X" + System.lineSeparator() +
      "*,*,*,*,X,X,X,X,X,X" + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X,X" + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X,X" + System.lineSeparator() +
      "*,X,X,X,X,X,X,X,X,X" + System.lineSeparator() +
      "*,*,*,*,*,X,X,X,X,X" + System.lineSeparator() +
      "X,X,X,X,*,*,*, , , " + System.lineSeparator() +
      "X,X,X,X,X,X,G, , , "

  def zeroRoutes: Array[Array[Cell]] = {
    val maze =
        "S, , , ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        "X,X,X, ,X,X,X,X,X,X," +
        " , , , ,X,X,X,X,X,X," +
        "X,X,X,X,X,X,X,X,X,X," +
        "X,X,X,X,X,X,X,X,X,X," +
        " ,X,X,X,X,X,X,X,X,X," +
        " , , , , ,X,X,X,X,X," +
        "X,X,X,X, , , , , , ," +
        "X,X,X,X,X,X,G, , , ,"

    convertToArray(maze)
  }

  def convertToArray(maze: String): Array[Array[Cell]] = {
    val all = maze.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

  def manhattanDistance(ml: Location, goal: Location): Double = {
    val xdist = Math.abs(ml.column - goal.column)
    val ydist = Math.abs(ml.row - goal.row)
    xdist + ydist
  }

}
