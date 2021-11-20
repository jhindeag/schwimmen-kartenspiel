package service

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class GameServiceTest {

    /***
     * Initialize game for testing
     */
    private fun setUpGame(): RootService {
        val rootService = RootService()
        rootService.mainMenuService.startGame("ab", "cd", null, "ef")
        return rootService
    }

    /***
     * Test the function hand out cards for all players
     */
    @Test
    fun handOutCards() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.handOutCards()
        //draw pile should be left with: 32 - 3*3 = 23 cards
        assertEquals(23, game.drawPile.remainingCards.size)
    }

    /***
     * Test the function reset placed cards on the table
     */
    @Test
    fun resetPlacedCards() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        val oldTable = game.placedCards
        val oldSize = game.drawPile.remainingCards.size
        rootService.gameService.resetPlacedCards()
        //they should not be the same now after
        //three new cards come to the table
        assertNotEquals(oldTable, game.placedCards)
        //and they should have three less card in draw pile
        assertEquals(oldSize - 3, game.drawPile.remainingCards.size)
    }

    /***
     * Test the function reset placed cards on the table
     * with invalid data
     */
    @Test
    fun resetPlacedCards2() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        for (i in 0..30) {
            game.drawPile.draw()
        }
        //the draw pile should have less than three cards to draw
        assertFailsWith<IllegalStateException>() {
            rootService.gameService.resetPlacedCards()
        }
    }

    /***
     * Test the function that checks the end condition after
     * each player turn in different scenarios
     */
    @Test
    fun afterPlayerTurn() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.handOutCards()
        rootService.actionService.pass()
        //passCount is only 1 and the next player hasn't knocked yet,
        //which means the game continues
        assertTrue(!rootService.gameService.afterPlayerTurn())
    }

    @Test
    fun afterPlayerTurn2() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.handOutCards()
        rootService.actionService.pass()
        rootService.actionService.pass()
        rootService.actionService.pass()
        //all players have passed and there are enough cards to be drawn,
        //which means the game continues
        assertTrue(!rootService.gameService.afterPlayerTurn())
    }

    @Test
    fun afterPlayerTurn3() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.handOutCards()
        for (i in 0..20) {
            game.drawPile.draw()
        }
        //the draw pile should have less than three cards by now
        rootService.actionService.pass()
        rootService.actionService.pass()
        rootService.actionService.pass()
        //all players have passed and there are not enough cards to be drawn,
        //which means the game ends
        assertTrue(rootService.currentGame == null)
    }

    @Test
    fun afterPlayerTurn4() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.handOutCards()
        rootService.actionService.knock()
        rootService.actionService.pass()
        rootService.actionService.takeAll()
        //the current player has knocked in the previous round,
        //which means the game should end now
        assertTrue(rootService.currentGame == null)
    }

    @Test
    fun endGame() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.gameService.endGame()
        //the game should be ended now
        assertTrue(rootService.currentGame == null)
    }
}