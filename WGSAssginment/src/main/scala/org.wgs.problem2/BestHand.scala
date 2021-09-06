package org.wgs.problem2

case class BestHand(handType: HandType, cardsOrganized: Seq[(String, Int, Seq[Card])])
