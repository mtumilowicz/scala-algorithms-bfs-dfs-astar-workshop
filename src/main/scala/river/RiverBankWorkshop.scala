package river

import scala.collection.mutable.ListBuffer

sealed trait RiverBankWorkshop {
  val policemen: Int
  val thieves: Int
  val max = 3

  val oppositeBankPolicemen: Int = max - policemen
  val oppositeBankThieves: Int = max - thieves

  def show(): String

  def toOppositeBank(p: Int, t: Int): RiverBankWorkshop

  def successors(): List[RiverBankWorkshop] = {
    val next = ListBuffer[RiverBankWorkshop]()
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

object RiverBankWorkshop {

  def show(path: List[RiverBankWorkshop]): List[String] =
    path.map(_.show())

  def checkIfGoalAchieved(bank: RiverBankWorkshop): Boolean =
    bank match {
      case LeftRiverBankWorkshop(policemen, thieves) => policemen == 0 && thieves == 0
      case RightRiverBankWorkshop(policemen, thieves) => policemen == bank.max && thieves == bank.max
    }
}

case class LeftRiverBankWorkshop(override val policemen: Int,
                                 override val thieves: Int) extends RiverBankWorkshop {

  override def show(): String =
    s"(${policemen}, ${thieves}) | (${oppositeBankPolicemen}, ${oppositeBankThieves}), boat = left}"

  override def toOppositeBank(p: Int, t: Int): RiverBankWorkshop =
    RightRiverBankWorkshop(oppositeBankPolicemen + p, oppositeBankThieves + t)
}

case class RightRiverBankWorkshop(override val policemen: Int,
                                  override val thieves: Int) extends RiverBankWorkshop {
  override def show(): String =
    s"(${oppositeBankPolicemen}, ${oppositeBankThieves}) | (${policemen}, ${thieves}), boat = right}"

  override def toOppositeBank(p: Int, t: Int): RiverBankWorkshop =
    LeftRiverBankWorkshop(oppositeBankPolicemen + p, oppositeBankThieves + t)
}
