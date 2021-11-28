package service

import entity.Card

/***
 * All four actions that the player can take in a
 * player turn are defined here
 */
class ActionService(private val rootService: RootService) : AbstractRefreshingService() {

    /***
     * Passes the current turn, increase the passCount,
     * then it will be checked, whether the end
     * of the game is reached or the game continues
     */
    fun pass() {
        val game = rootService.currentGame
        checkNotNull(game)
        game.passCount++
        if (game.passCount == game.players.size) {
            if (game.drawPile.remainingCards.size >= 3) {
                rootService.gameService.resetPlacedCards()
                onAllRefreshables { refreshAfterPlacedCardsChange() }
            }
        }
        if (rootService.gameService.afterPlayerTurn()) {
            rootService.gameService.endGame()
        }
    }

    /***
     * Trades one card in hand with one card on table,
     * then it will be checked, whether the end
     * of the game is reached or the game continues
     *
     * @param cardInHand: the card in hand to be traded
     * @param cardOnTable: the card on table to be traded
     */
    fun tradeOne(cardInHand: Card, cardOnTable: Card) {
        val game = rootService.currentGame
        checkNotNull(game)
        game.passCount = 0
        //find the index of both card on table and card in hand
        var handIndex = 0
        var tableIndex = 0
        for (i in 0..2) {
            if (cardInHand.toString() == game.currentPlayer.hand[i].toString()) {
                handIndex = i
            }
            if (cardOnTable.toString() == game.placedCards[i].toString()) {
                tableIndex = i
            }
        }

        //swap the two cards
        game.currentPlayer.hand[handIndex] =
            game.placedCards[tableIndex].also {
                game.placedCards[tableIndex] =
                    game.currentPlayer.hand[handIndex]
            }
        onAllRefreshables {
            refreshAfterPlacedCardsChange()
        }
        if (rootService.gameService.afterPlayerTurn()) {
            rootService.gameService.endGame()
        }
    }

    /***
     * Trades all cards in hand with all cards on table,
     * then it will be checked, whether the end
     * of the game is reached or the game continues
     */
    fun takeAll() {
        val game = rootService.currentGame
        checkNotNull(game)
        game.passCount = 0
        game.currentPlayer.hand = game.placedCards.also {
            game.placedCards = game.currentPlayer.hand
        }
        onAllRefreshables {
            refreshAfterPlacedCardsChange()
        }
        if (rootService.gameService.afterPlayerTurn()) {
            rootService.gameService.endGame()
        }
    }

    /***
     * Knocks, changes the status of the player and ends turn,
     * then it will be checked, whether the end
     * of the game is reached or the game continues
     *
     */
    fun knock() {
        val game = rootService.currentGame
        checkNotNull(game)
        game.passCount = 0
        game.currentPlayer.hasKnocked = true
        if (rootService.gameService.afterPlayerTurn()){
            rootService.gameService.endGame()
        }
    }
}