package org.wgs.problem2

import scala.io.Source._

object FindThePokerChamp {
  def main(args: Array[String]): Unit = {
    println(getWinner("poker.txt"))
  }

  def getWinner(fileName: String): Int = {
    val inputFile = fromResource(fileName)
    val lines: Iterator[String] = inputFile.getLines()
    val inputHands: Seq[Seq[String]] = lines.map(_.split(" ").toSeq).toSeq

    def getPlayer1Cards(player1: Seq[String]) = {
      player1.map(x => new Card(x.charAt(0).toString, x.charAt(1) match {
        case 'S' => Spade
        case 'D' => Diamond
        case 'H' => Heart
        case 'C' => Club
      }, x))
    }

    var count = 0
    inputHands.foreach {
      hands => {
        val (firstColumn, secondsColumn) = hands.splitAt(5)
        val player1WithCards = new Hand(getPlayer1Cards(firstColumn))
        val player2WithCards = new Hand(getPlayer1Cards(secondsColumn))
        val winner = Helper.getResult(player1WithCards, player2WithCards)
        if (winner == player1WithCards) count += 1
      }
    }
    count
  }

}