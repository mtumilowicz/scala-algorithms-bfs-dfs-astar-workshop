sealed class Cell(val code: String) {
  def show(): String = code
}
case class Empty() extends Cell(" ")
case class Blocked() extends Cell("X")
case class Start() extends Cell("S")
case class Goal() extends Cell("G")
case class Path() extends Cell("*")
