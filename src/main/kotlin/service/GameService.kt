package service

/***
 * The actions that manipulate the game
 * are defined here
 */
class GameService(private val rootService: RootService) : AbstractRefreshingService() {

    /***
     * Gives all player three cards at the beginning of the game
     */
    fun handOutCards() {
        val game = rootService.currentGame
        checkNotNull(game)
        for (i in 0 until game.players.size) {
            game.players[i].hand =
                arrayListOf(
                    game.drawPile.draw(),
                    game.drawPile.draw(),
                    game.drawPile.draw()
                )
        }
        onAllRefreshables { refreshAfterPlayerStateChange(game.currentPlayer) }
    }

    /***
     * Updates the new drawn cards on table
     *
     * @throws IllegalStateException when there are not enough cards to replace
     */
    fun resetPlacedCards() {
        val game = rootService.currentGame
        checkNotNull(game)
        game.passCount = 0
        if (game.drawPile.remainingCards.size >= 3) {
            game.placedCards = arrayListOf(
                game.drawPile.draw(),
                game.drawPile.draw(),
                game.drawPile.draw()
            )
        } else {
            throw IllegalStateException()
        }
        onAllRefreshables { refreshAfterPlacedCardsChange() }
    }


    /***
     * Ends the current turn and checks the game ending condition
     */
    fun afterPlayerTurn(): Boolean {
        val game = rootService.currentGame
        checkNotNull(game)
        game.nextPlayer()
        onAllRefreshables { refreshAfterPlayerStateChange(game.currentPlayer) }
        return (
                ((game.passCount == game.players.size) && (game.drawPile.remainingCards.size < 3))
                        || game.currentPlayer.hasKnocked)
    }

    /***
     * Ends the game and shows scoreboard
     */
    fun endGame() {
        val game = rootService.currentGame
        checkNotNull(game)
        game.players = game.players.sortedByDescending { it.points }
        onAllRefreshables { refreshAfterEndGame() }
        rootService.currentGame = null
    }
}
