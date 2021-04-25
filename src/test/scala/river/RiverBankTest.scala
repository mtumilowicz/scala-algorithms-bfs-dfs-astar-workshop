package river

import algorithms.answer.BfsAnswer
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class RiverBankTest extends AnyFeatureSpec with GivenWhenThen {

  Feature("solve the riddle") {
    Scenario("3 policemen, 3 thieves") {
      Given("left bank state")
      val start = LeftRiverBank(3, 3)

      When("solve")
      val solution = BfsAnswer.run(start, RiverBank.checkIfGoalAchieved, (x: RiverBank) => x.successors())

      Then("is solvable")
      solution.map(_.toPath).map(RiverBank.show).orNull shouldBe list
    }
  }

  private def list: List[String] =
    List(
      "(3, 3) | (0, 0), boat = left}",
      "(3, 1) | (0, 2), boat = right}",
      "(3, 2) | (0, 1), boat = left}",
      "(3, 0) | (0, 3), boat = right}",
      "(3, 1) | (0, 2), boat = left}",
      "(1, 1) | (2, 2), boat = right}",
      "(2, 2) | (1, 1), boat = left}",
      "(0, 2) | (3, 1), boat = right}",
      "(0, 3) | (3, 0), boat = left}",
      "(0, 1) | (3, 2), boat = right}",
      "(1, 1) | (2, 2), boat = left}",
      "(0, 0) | (3, 3), boat = right}"
    )

}
