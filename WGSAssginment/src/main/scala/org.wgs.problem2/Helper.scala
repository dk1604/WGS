package org.wgs.problem2

object Helper {
  val listOfBasicCards = (2 to 9).map(number => (number.toString, number)).toList
  val values = listOfBasicCards ::: List(("T", 10), ("J", 11), ("Q", 12), ("K", 13), ("A", 14))


  def getValueForRank(cardRank: String): Int = {
    values.toMap.get(cardRank).get
  }

  def getResult(player1: Hand, player2: Hand): Hand = {
    val player1BestHand = player1.getBestHand
    val player2BestHand = player2.getBestHand

    if (player1BestHand.handType.precedenceValue > player2BestHand.handType.precedenceValue) {
      println(s"*********** player1 wins for '${player1BestHand.handType}'*************\n")
      player1
    } else if (player1BestHand.handType.precedenceValue == player2BestHand.handType.precedenceValue) {
      if (getValueForRank(player1BestHand.cardsOrganized.head._1) > getValueForRank(player2BestHand.cardsOrganized.head._1)) {
        println(s"*********** Both has same best hand but player1 has '${player1BestHand.handType}' with high card so player1 wins ***********\n")
        player1
      } else {
        println(s"***********Both has same best hand but player2 has '${player2BestHand.handType}' with high card so player2 wins ***********\n")
        player2
      }
    } else {
      println(s"*********** player2 wins for '${player2BestHand.handType}'***********\n")
      player2
    }
  }
}
