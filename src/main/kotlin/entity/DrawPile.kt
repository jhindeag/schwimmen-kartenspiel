package entity

import tools.aqua.bgw.util.Stack

class DrawPile {
    val remainingCards: Stack<Card> = Stack()
    fun add(element: Card) {
        remainingCards.push(element)
    }

    fun draw(): Card {
        return remainingCards.pop()
    }

    fun init() {
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