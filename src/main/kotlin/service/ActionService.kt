package service

import entity.Card

class ActionService {
    private val gameSer = MainMenuService().gameServ
    fun pass() {
        gameSer.currentGame.passCount++
        if (gameSer.currentGame.passCount == gameSer.currentGame.players.size) {
            if (gameSer.currentGame.drawPile.remainingCards.size >= 3) {
                gameSer.resetPlacedCards()
            } else {
                gameSer.endGame()
            }
        }
    }

    fun tradeOne(cardInHand: Card, cardOnTable: Card) {
        var handIndex = 0
        var tableIndex = 0
        for (i in 0..3) {
            if (cardInHand.toString() == gameSer.currentGame.currentPlayer.hand[i].toString()) {
                handIndex = i
            }
            if (cardOnTable.toString() == gameSer.currentGame.placedCards[i].toString()) {
                tableIndex = i
            }
        }
        gameSer.currentGame.currentPlayer.hand[handIndex] = gameSer.currentGame.placedCards[tableIndex]
    }

    fun takeAll() {
        gameSer.currentGame.currentPlayer.hand = gameSer.currentGame.placedCards
    }

    fun knock() {
        gameSer.currentGame.currentPlayer.hasKnocked = true
    }
}