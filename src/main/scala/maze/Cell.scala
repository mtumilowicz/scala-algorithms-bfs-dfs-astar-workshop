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

  def from(code: String): Option[Cell] = {
    code match {
      case "X" => Some(Blocked())
      case "S" => Some(Start())
      case "G" => Some(Goal())
      case " " => Some(Empty())
      case _ => None
    }
  }
}
