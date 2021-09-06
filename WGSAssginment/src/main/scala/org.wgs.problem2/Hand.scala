package org.wgs.problem2

class Hand(val cards: Seq[Card]) {

  val orderedCards: Seq[(String, Int, Seq[Card])] = orderCardsByRank(cards)
  println(s"Cards with their rank ${orderedCards}")

  def showCards(): String = {
    orderedCards.flatMap(_._3.toList).mkString(" ")
  }

  def getBestHand: BestHand = {
    val handType = if (isRoyalFlush) RoyalFlush else if (isStraightFlush) StraightFlush else if (isFourOfAKind) FourOfAKind else if (isFullHouse) FullHouse else if (isFlush) Flush else if (isStraight) Straight else if (isThreeOfAKind) ThreeOfAKind else if (isTwoPairs) TwoPair else if (isOnePairs) OnePair else HighCard
    BestHand(handType, orderedCards)
  }

  def isRoyalFlush = {
    val royalCards = List("T", "J", "Q", "K", "A")
    val result = orderedCards.map(x => royalCards.contains(x._1)).forall(_.equals(true)) && getCardsGroupedBySuit(cards).size == 1
    result
  }

  def isStraightFlush = {
    val cardsGroupedBySuit = getCardsGroupedBySuit(cards)
    cardsGroupedBySuit.size == 1 && areCardsInSequenceByTheirRank
  }

  def isFourOfAKind = orderedCards.size == 2 && (orderedCards.head._3.size == 4 || orderedCards.tail.head._3.size == 4)

  def isFullHouse = orderedCards.size == 2 && (orderedCards.head._3.size == 3 || orderedCards.tail.head._3.size == 3)

  def isFlush = getCardsGroupedBySuit(cards).size == 1

  def isStraight = areCardsInSequenceByTheirRank

  def isThreeOfAKind = orderedCards.map(_._3.size).contains(3)

  def isTwoPairs = orderedCards.map(_._3.size).count(_ == 2) == 2

  def isOnePairs = orderedCards.map(_._3.size).count(_ == 2) == 1

  def isHighCard = orderedCards.size == 5

  private def areCardsInSequenceByTheirRank() = {
    val getCardsAsPerTheirRank: Seq[Int] = orderedCards.flatMap(x => x._3.map(y => Helper.getValueForRank(y.rank)))
    val areCardsInSequence = getCardsAsPerTheirRank.sliding(2).collect {
      case Seq(a, b) =>
        a - b
    }.toList.forall(_ == 1)
    areCardsInSequence
  }

  private def getCardsGroupedBySuit(cards: Seq[Card]) = {
    val cardsGroupedBySuit: Map[String, Seq[Card]] = cards groupBy {
      _.suit.displayId
    }
    cardsGroupedBySuit
  }

  private def orderCardsByRank(cards: Seq[Card]): Seq[(String, Int, Seq[Card])] = {
    val cardsGrouped: Map[String, Seq[Card]] = cards groupBy {
      _.rank
    }
    val cardsGroupedSorted: Seq[(String, Int, Seq[Card])] = cardsGrouped.map(x => (x._1, x._2.size, x._2)).toSeq.sortBy(_._1)(new Ordering[String]() {
      override def compare(x: String, y: String) = {
        Helper.getValueForRank(y).compareTo(Helper.getValueForRank(x))
      }
    })
    cardsGroupedSorted
  }
}