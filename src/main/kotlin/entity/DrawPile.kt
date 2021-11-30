package entity

import tools.aqua.bgw.util.Stack

/**
 * this class defines the skat draw pile with 32 cards
 * and the method to draw cards
 */
class DrawPile {
    val remainingCards: Stack<Card> = Stack()

    /**
     * Die Methode zieht die oberste Karte aus dem Ziehstapel
     *
     * @throws IllegalStateException wenn es kein Objekt im Stack gibt
     * @return Das oberste Karte-Objekt im Stack
     */
    fun draw(): Card {
        if (remainingCards.size == 0) {
            throw IllegalStateException("There is no card to draw")
        } else {
            return remainingCards.pop()
        }
    }

    /**
     * Die Methode fügt alle 32 Skat-Karten in den Ziehstapel hinzu.
     * Dann wird der Ziehstapel gemischt, damit die Reihenfolge von Karten zufällig am Anfang jedes Spiels ist
     */
    init {
        var value: CardValue = CardValue.ACE
        var suit: CardSuit = CardSuit.DIAMONDS
        for (i in 1..4) {
            for (j in 6..13) {
                when (i) {
                    1 -> {
                        suit = CardSuit.CLUBS
                    }
                    2 -> {
                        suit = CardSuit.HEARTS
                    }
                    3 -> {
                        suit = CardSuit.SPADES
                    }
                    4 -> {
                        suit = CardSuit.DIAMONDS
                    }
                }
                when (j) {
                    6 -> {
                        value = CardValue.ACE
                    }
                    7 -> {
                        value = CardValue.SEVEN
                    }
                    8 -> {
                        value = CardValue.EIGHT
                    }
                    9 -> {
                        value = CardValue.NINE
                    }
                    10 -> {
                        value = CardValue.TEN
                    }
                    11 -> {
                        value = CardValue.JACK
                    }
                    12 -> {
                        value = CardValue.QUEEN
                    }
                    13 -> {
                        value = CardValue.KING
                    }
                }
                remainingCards.push(Card(suit, value))
            }
        }
        remainingCards.shuffle()
    }
}