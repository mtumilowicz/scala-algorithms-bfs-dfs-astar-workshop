package river

import scala.collection.mutable.ListBuffer

sealed trait Bank {
  val policemen: Int = 3
  val thieves: Int = 3
  val max = 3

  val oppositeBankPolicemen: Int = max - policemen
  val oppositeBankThieves: Int = max - thieves

  def show(): String

  def toOppositeBank(p: Int, t: Int): Bank

  def successors(): List[Bank] = {
    val next = ListBuffer[Bank]()
    if (policemen > 1) next += toOppositeBank(2, 0)
    if (policemen > 0) next += toOppositeBank(1, 0)
    if (thieves > 1) next += toOppositeBank(0, 2)
    if (thieves > 0) next += toOppositeBank(0, 1)
    if (thieves > 0 && policemen > 0) next += toOppositeBank(1, 1)

    next.filter(_.isLegal).toList
  }

  def isLegal: Boolean = {
    if (this.policemen < this.thieves && this.policemen > 0) return false
    if (this.oppositeBankPolicemen < this.oppositeBankThieves && this.oppositeBankPolicemen > 0) return false
    true
  }
}

object Bank {

  def show(path: List[Bank]): List[String] =
    path.map(_.show())

  def checkIfGoalAchieved(bank: Bank): Boolean =
    bank match {
      case LeftBank(policemen, thieves) => policemen == 0 && thieves == 0
      case RightBank(policemen, thieves) => policemen == bank.max && thieves == bank.max
    }
}

case class LeftBank(override val policemen: Int,
                    override val thieves: Int) extends Bank {

  override def show(): String =
    s"(${policemen}, ${thieves}) | (${oppositeBankPolicemen}, ${oppositeBankThieves}), boat = left}"

  override def toOppositeBank(p: Int, t: Int): Bank =
    RightBank(oppositeBankPolicemen + p, oppositeBankThieves + t)
}

case class RightBank(override val policemen: Int,
                     override val thieves: Int) extends Bank {
  override def show(): String =
    s"(${oppositeBankPolicemen}, ${oppositeBankThieves}) | (${policemen}, ${thieves}), boat = right}"

  override def toOppositeBank(p: Int, t: Int): Bank =
    LeftBank(oppositeBankPolicemen + p, oppositeBankThieves + t)
}
