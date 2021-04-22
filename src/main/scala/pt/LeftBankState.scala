package pt

import scala.collection.mutable.ListBuffer

case class LeftBankState(
                          policemen: Int,
                          thieves: Int,
                          boat: Boolean
                        ) {

  val MAX_NUM: Int = 3
  val rightBankPolicemen: Int = MAX_NUM - policemen
  val rightBankThieves: Int = MAX_NUM - thieves

  def show(): String =
    s"(${policemen}, ${thieves}) | (${rightBankPolicemen}, ${rightBankThieves}), boat = ${if (boat) "left" else "right"}"

  def checkIfGoalAchieved(leftBankState: LeftBankState): Boolean = leftBankState.isLegal &&
    (leftBankState.rightBankThieves == MAX_NUM) &&
    (leftBankState.rightBankPolicemen == MAX_NUM)

  def successors(): List[LeftBankState] = {
    val next = ListBuffer[LeftBankState]()
    if (boat) {
      if (policemen > 1) next += toRightBank(2, 0)
      if (policemen > 0) next += toRightBank(1, 0)
      if (thieves > 1) next += toRightBank(0, 2)
      if (thieves > 0) next += toRightBank(0, 1)
      if (thieves > 0 && policemen > 0) next += toRightBank(1, 1)
    }
    else {
      if (rightBankPolicemen > 1) next += toLeftBank(2, 0)
      if (rightBankPolicemen > 0) next += toLeftBank(1, 0)
      if (rightBankThieves > 1) next += toLeftBank(0, 2)
      if (rightBankThieves > 0) next += toLeftBank(0, 1)
      if (rightBankThieves > 0 && rightBankPolicemen > 0) next += toLeftBank(1, 1)
    }

    next.filter(_.isLegal).toList
  }

  def isLegal: Boolean = {
    if (this.policemen < this.thieves && this.policemen > 0) return false
    if (this.rightBankPolicemen < this.rightBankThieves && this.rightBankPolicemen > 0) return false
    true
  }

  def toRightBank(p: Int, t: Int): LeftBankState =
    LeftBankState(policemen - p, thieves - t, false)

  def toLeftBank(p: Int, t: Int): LeftBankState =
    LeftBankState(policemen + p, thieves + t, true)
}

object LeftBankState {

  def displaySolution(path: List[LeftBankState]): Unit = {
    println(path.map(_.show()).mkString(System.lineSeparator()))
  }
}
