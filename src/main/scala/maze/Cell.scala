package maze

sealed class Cell(val code: String) {
  def show(): String = code
}

object Cell {
  case class Empty() extends Cell(" ")

  case class Blocked() extends Cell("X")

  case class Start() extends Cell("S")

  case class Goal() extends Cell("G")

  case class Path() extends Cell("*")

  def from(code: String): Cell = {
    code match {
      case "X" => Blocked()
      case "S" => Start()
      case "G" => Goal()
      case " " => Empty()
      case _ => null
    }
  }
}
