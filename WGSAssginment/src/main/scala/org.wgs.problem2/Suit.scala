package org.wgs.problem2

trait Suit {
  val displayId: String
  val name: String
}

case object Diamond extends Suit {
  override val displayId: String = "D"
  override val name: String = "Diamond"
}

case object Spade extends Suit {
  override val displayId: String = "S"
  override val name: String = "Spade"
}

case object Heart extends Suit {
  override val displayId: String = "H"
  override val name: String = "Heart"
}

case object Club extends Suit {
  override val displayId: String = "C"
  override val name: String = "Club"
}