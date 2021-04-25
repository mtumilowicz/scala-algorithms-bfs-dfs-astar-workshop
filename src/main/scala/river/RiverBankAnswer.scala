package river

import scala.collection.mutable.ListBuffer

sealed trait RiverBankAnswer {
  val policemen: Int
  val thieves: Int
  val max = 3

  val oppositeBankPolicemen: Int = max - policemen
  val oppositeBankThieves: Int = max - thieves

  def show(): String

  def toOppositeBank(p: Int, t: Int): RiverBankAnswer

  def successors(): List[RiverBankAnswer] = {
    val next = ListBuffer[RiverBankAnswer]()
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

object RiverBankAnswer {

  def show(path: List[RiverBankAnswer]): List[String] =
    path.map(_.show())

  def checkIfGoalAchieved(bank: RiverBankAnswer): Boolean =
    bank match {
      case LeftRiverBankAnswer(policemen, thieves) => policemen == 0 && thieves == 0
      case RightRiverBankAnswer(policemen, thieves) => policemen == bank.max && thieves == bank.max
    }
}

case class LeftRiverBankAnswer(override val policemen: Int,
                               override val thieves: Int) extends RiverBankAnswer {

  override def show(): String =
    s"(${policemen}, ${thieves}) | (${oppositeBankPolicemen}, ${oppositeBankThieves}), boat = left}"

  override def toOppositeBank(p: Int, t: Int): RiverBankAnswer =
    RightRiverBankAnswer(oppositeBankPolicemen + p, oppositeBankThieves + t)
}

case class RightRiverBankAnswer(override val policemen: Int,
                                override val thieves: Int) extends RiverBankAnswer {
  override def show(): String =
    s"(${oppositeBankPolicemen}, ${oppositeBankThieves}) | (${policemen}, ${thieves}), boat = right}"

  override def toOppositeBank(p: Int, t: Int): RiverBankAnswer =
    LeftRiverBankAnswer(oppositeBankPolicemen + p, oppositeBankThieves + t)
}
