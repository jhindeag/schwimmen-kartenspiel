package service

import entity.Card
import entity.CardSuit
import entity.CardValue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class ActionServiceTest {

    /***
     * Initialize game for testing
     */
    private fun setUpGame(): RootService {
        val rootService = RootService()
        rootService.mainMenuService.startGame("ab", "cd", "ef", null)
        rootService.gameService.handOutCards()
        return rootService
    }

    /***
     * Test for the function pass
     */
    @Test
    fun pass() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        val oldTable = game.placedCards
        rootService.actionService.pass()
        rootService.actionService.pass()
        rootService.actionService.pass()
        //the old cards on table should be replaced
        assertNotEquals(oldTable, game.placedCards)
    }

    /***
     * Test for the function pass with less than 3 cards in draw pile
     */
    @Test
    fun pass2() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        //32 - 3 * 3 = 23 Cards left in draw pile
        for (i in 0..20) {
            game.drawPile.draw()
        }
        //It should have less than 3 cards in draw pile now
        println(game.passCount)
        rootService.actionService.pass()
        rootService.actionService.pass()
        rootService.actionService.pass()
        //All players have passed and there are not enough cards,
        //therefore the game should end
        assertTrue(rootService.currentGame == null)
    }

    /***
     * Test for the function trade one card
     */
    @Test
    fun tradeOne() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        //Initialize data for testing
        game.placedCards = arrayListOf(
            Card(CardSuit.SPADES, CardValue.QUEEN),
            Card(CardSuit.CLUBS, CardValue.ACE),
            Card(CardSuit.DIAMONDS, CardValue.TWO)
        )
        val player = game.currentPlayer
        player.hand = arrayListOf(
            Card(CardSuit.DIAMONDS, CardValue.SEVEN),
            Card(CardSuit.SPADES, CardValue.SIX),
            Card(CardSuit.CLUBS, CardValue.NINE)
        )
        //trades cards
        rootService.actionService.tradeOne(
            Card(CardSuit.DIAMONDS, CardValue.SEVEN),
            Card(CardSuit.CLUBS, CardValue.ACE)
        )
        //check if the cards are traded correctly
        assertEquals(Card(CardSuit.CLUBS, CardValue.ACE), player.hand[0])
        assertEquals(Card(CardSuit.DIAMONDS, CardValue.SEVEN), game.placedCards[1])
    }

    /***
     * Test for the function trade all cards
     */
    @Test
    fun takeAll() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        //Initialize data for testing
        game.placedCards = arrayListOf(
            Card(CardSuit.SPADES, CardValue.QUEEN),
            Card(CardSuit.CLUBS, CardValue.ACE),
            Card(CardSuit.DIAMONDS, CardValue.TWO)
        )
        val player = game.currentPlayer
        player.hand = arrayListOf(
            Card(CardSuit.DIAMONDS, CardValue.SEVEN),
            Card(CardSuit.SPADES, CardValue.SIX),
            Card(CardSuit.CLUBS, CardValue.NINE)
        )
        val oldTable = game.placedCards
        val oldPlayer = player.hand
        //trades cards
        rootService.actionService.takeAll()
        //check if the cards are traded correctly
        assertEquals(player.hand, oldTable)
        assertEquals(game.placedCards, oldPlayer)
    }

    /***
     * Test for the function knock
     */
    @Test
    fun knock() {
        val rootService = setUpGame()
        val game = rootService.currentGame
        checkNotNull(game)
        rootService.actionService.knock()
        rootService.actionService.pass()
        rootService.actionService.takeAll()
        //it's the turn of the player, who has knocked,
        //therefore the game should be ended by now
        assertTrue(rootService.currentGame == null)
    }
}