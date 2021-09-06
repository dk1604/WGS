package org.wgs.problem2

sealed trait HandType {
  val precedenceValue: Int
}

case object RoyalFlush extends HandType {
  override val precedenceValue: Int = 1000
}

case object StraightFlush extends HandType {
  override val precedenceValue: Int = 900
}

case object FourOfAKind extends HandType {
  override val precedenceValue: Int = 800
}

case object FullHouse extends HandType {
  override val precedenceValue: Int = 700
}

case object Flush extends HandType {
  override val precedenceValue: Int = 600
}

case object Straight extends HandType {
  override val precedenceValue: Int = 500
}

case object ThreeOfAKind extends HandType {
  override val precedenceValue: Int = 400
}

case object TwoPair extends HandType {
  override val precedenceValue: Int = 300
}

case object OnePair extends HandType {
  override val precedenceValue: Int = 200
}

case object HighCard extends HandType {
  override val precedenceValue: Int = 100
}