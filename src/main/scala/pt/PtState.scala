package pt

import scala.collection.mutable.ListBuffer

case class PtState(
                    wm: Int, // west bank policemen
                    wc: Int, // west bank thieves
                    boat: Boolean // is boat on west bank?
                  ) {
  val MAX_NUM: Int = 3
  val em: Int = MAX_NUM - wm
  val ec: Int = MAX_NUM - wc

  override def toString: String =
    s"On the west bank there are ${wm} policemen and ${wc} thieves. \n " +
      s"On the east bank there are ${em} policemen and ${ec} thieves. \n  " +
      s"The boat is on the ${if (boat) "west" else "east"} bank."

  def goalTest(ptState: PtState): Boolean = isLegal(ptState) && (ptState.em == MAX_NUM) && (ptState.ec == MAX_NUM)

  def isLegal(ptState: PtState): Boolean = {
    if (ptState.wm < ptState.wc && ptState.wm > 0) return false
    if (ptState.em < ptState.ec && ptState.em > 0) return false
    true
  }

  def successors(mcs: PtState): List[PtState] = {
    val sucs = ListBuffer[PtState]()
    if (mcs.boat) { // boat on west bank
      if (mcs.wm > 1) sucs.addOne(PtState(mcs.wm - 2, mcs.wc, !mcs.boat))
      if (mcs.wm > 0) sucs.addOne(PtState(mcs.wm - 1, mcs.wc, !mcs.boat))
      if (mcs.wc > 1) sucs.addOne(PtState(mcs.wm, mcs.wc - 2, !mcs.boat))
      if (mcs.wc > 0) sucs.addOne(PtState(mcs.wm, mcs.wc - 1, !mcs.boat))
      if (mcs.wc > 0 && mcs.wm > 0) sucs.addOne(PtState(mcs.wm - 1, mcs.wc - 1, !mcs.boat))
    }
    else { // boat on east bank
      if (mcs.em > 1) sucs.addOne(PtState(mcs.wm + 2, mcs.wc, !mcs.boat))
      if (mcs.em > 0) sucs.addOne(PtState(mcs.wm + 1, mcs.wc, !mcs.boat))
      if (mcs.ec > 1) sucs.addOne(PtState(mcs.wm, mcs.wc + 2, !mcs.boat))
      if (mcs.ec > 0) sucs.addOne(PtState(mcs.wm, mcs.wc + 1, !mcs.boat))
      if (mcs.ec > 0 && mcs.em > 0) sucs.addOne(PtState(mcs.wm + 1, mcs.wc + 1, !mcs.boat))
    }
    sucs.filter(x => x.isLegal(x)).toList
  }
}

object PtState {
  def displaySolution(path: List[PtState]): Unit = {
    if (path.isEmpty) { // sanity check
      return;
    }
    var oldState = path.head
    System.out.println(oldState)
    for (currentState <- path.slice(1, path.length)) {
      if (currentState.boat) {
        System.out.printf(s"${oldState.em - currentState.em} policemen and ${oldState.ec - currentState.ec} " +
          s"thieves moved from the east bank to the west bank.")
      } else {
        System.out.printf(s"${oldState.wm - currentState.wm} policemen and ${oldState.wc - currentState.wc} " +
          s"thieves moved from the west bank to the east bank")
      }
      System.out.println(currentState)
      oldState = currentState
    }
  }
}
