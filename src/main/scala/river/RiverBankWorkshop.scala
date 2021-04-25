package river

sealed trait RiverBankWorkshop {
  val policemen: Int
  val thieves: Int
  val max = 3

  val oppositeBankPolicemen: Int = max - policemen
  val oppositeBankThieves: Int = max - thieves

  def show(): String

  def toOppositeBank(p: Int, t: Int): RiverBankWorkshop

  def successors(): List[RiverBankWorkshop] = {
    // define successors in terms of:
    // policemen (two cases)
    // thieves (two cases)
    // policemen and thieves - 1 case
    // hint: toOppositeBank
    // filter only legals, hint: isLegal
    List()
  }

  def isLegal: Boolean = {
    // policemen cannot be less that thieves if there are any
    // opposite bank the same
    // otherwise true
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
