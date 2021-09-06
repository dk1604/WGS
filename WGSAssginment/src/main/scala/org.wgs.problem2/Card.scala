package org.wgs.problem2

class Card(val rank: String, val suit: Suit, val card: String) {
  class Card(val rank: String, val suit: Suit, val card: String) {
    override def toString: String = card
  }

  object Card {
    def apply(rank: String, suit: Suit): Card = new Card(rank, suit, rank + suit.displayId)
  }
}
