package river

import scala.collection.mutable.ListBuffer

sealed trait RiverBank {
  val policemen: Int
  val thieves: Int
  val max = 3

  val oppositeBankPolicemen: Int = max - policemen
  val oppositeBankThieves: Int = max - thieves

  def show(): String

  def toOppositeBank(p: Int, t: Int): RiverBank

  def successors(): List[RiverBank] = {
    val next = ListBuffer[RiverBank]()
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

object RiverBank {

  def show(path: List[RiverBank]): List[String] =
    path.map(_.show())

  def checkIfGoalAchieved(bank: RiverBank): Boolean =
    bank match {
      case LeftRiverBank(policemen, thieves) => policemen == 0 && thieves == 0
      case RightRiverBank(policemen, thieves) => policemen == bank.max && thieves == bank.max
    }
}

case class LeftRiverBank(override val policemen: Int,
                         override val thieves: Int) extends RiverBank {

  override def show(): String =
    s"(${policemen}, ${thieves}) | (${oppositeBankPolicemen}, ${oppositeBankThieves}), boat = left}"

  override def toOppositeBank(p: Int, t: Int): RiverBank =
    RightRiverBank(oppositeBankPolicemen + p, oppositeBankThieves + t)
}

case class RightRiverBank(override val policemen: Int,
                          override val thieves: Int) extends RiverBank {
  override def show(): String =
    s"(${oppositeBankPolicemen}, ${oppositeBankThieves}) | (${policemen}, ${thieves}), boat = right}"

  override def toOppositeBank(p: Int, t: Int): RiverBank =
    LeftRiverBank(oppositeBankPolicemen + p, oppositeBankThieves + t)
}
