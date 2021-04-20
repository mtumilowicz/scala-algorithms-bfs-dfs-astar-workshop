package algorithms

import maze.{Cell, Location, Maze}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class DfsTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("solve the maze") {
    Scenario("unsolvable maze") {
      Given("zero route")
      val maze = Maze(zeroRoutes, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = Dfs.run(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is empty")
      solution shouldBe None
    }

    Scenario("solvable maze with one route") {
      Given("one route")
      val maze = Maze(oneRoute, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = Dfs.run(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).orNull shouldBe oneRouteSolution
    }

    Scenario("solvable maze with two routes") {
      Given("one route")
      val maze = Maze(twoRoutes, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = Dfs.run(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).orNull shouldBe twoRouteSolution
    }

    Scenario("solvable maze with many routes") {
      Given("one route")
      val maze = Maze(manyRoutes, Location(0, 0), Location(9, 9))

      When("solve")
      val solution = Dfs.run(maze.start, maze.checkIfGoalAchieved, maze.successors)

      Then("is solvable")
      solution.map(_.toPath).orNull shouldBe manyRouteSolution
    }
  }

  def manyRoutes: Array[Array[Cell]] = {
    val lab =
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

    val all = lab.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

  def manyRouteSolution: List[Location] =
    List(
      Location(0,0), Location(0,1), Location(0,2), Location(0,3), Location(0,4), Location(0,5), Location(0,6),
      Location(0,7), Location(0,8), Location(0,9), Location(1,9), Location(2,9), Location(2,8), Location(2,7),
      Location(2,6), Location(2,5), Location(2,4), Location(2,3), Location(2,2), Location(2,1), Location(2,0),
      Location(3,0), Location(4,0), Location(4,1), Location(4,2), Location(4,3), Location(4,4), Location(4,5),
      Location(4,6), Location(4,7), Location(4,8), Location(4,9), Location(5,9), Location(6,9), Location(6,8),
      Location(6,7), Location(6,6), Location(6,5), Location(6,4), Location(6,3), Location(6,2), Location(6,1),
      Location(6,0), Location(7,0), Location(8,0), Location(8,1), Location(8,2), Location(8,3), Location(8,4),
      Location(8,5), Location(8,6), Location(8,7), Location(8,8), Location(8,9), Location(9,9))

  def twoRoutes: Array[Array[Cell]] = {
    val lab =
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

    val all = lab.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

  def twoRouteSolution: List[Location] =
    List(
      Location(0,0), Location(0,1), Location(0,2), Location(0,3), Location(0,4), Location(0,5), Location(0,6),
      Location(0,7), Location(0,8), Location(0,9), Location(1,9), Location(2,9), Location(3,9), Location(4,9),
      Location(5,9), Location(6,9), Location(7,9), Location(8,9), Location(9,9))

  def oneRoute: Array[Array[Cell]] = {
    val lab =
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

    val all = lab.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

  def oneRouteSolution: List[Location] =
    List(
      Location(0,0), Location(0,1), Location(0,2), Location(0,3), Location(1,3), Location(2,3), Location(3,3),
      Location(3,2), Location(3,1), Location(3,0), Location(4,0), Location(5,0), Location(6,0), Location(7,0),
      Location(7,1), Location(7,2), Location(7,3), Location(7,4), Location(8,4), Location(8,5), Location(8,6),
      Location(8,7), Location(8,8), Location(8,9), Location(9,9))

  def zeroRoutes: Array[Array[Cell]] = {
    val lab =
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

    val all = lab.split(",").flatMap(Cell.from)
    all.grouped(10).toArray
  }

}
