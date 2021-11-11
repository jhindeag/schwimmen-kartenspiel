package entity

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class GameTest {

    /***
     * Testet die Initialisierung mit ungültigen Testdaten
     */
    @Test
    fun initialize() {
        // Test: initialize Game schlaegt fehl
        assertFailsWith<IllegalArgumentException> {
            val game = Game(
                listOf(
                    Player(
                        "A", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                            Card(CardSuit.CLUBS, CardValue.JACK),
                            Card(CardSuit.SPADES, CardValue.THREE)
                        )
                    ),
                    Player(
                        "B", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                            Card(CardSuit.CLUBS, CardValue.TEN),
                            Card(CardSuit.SPADES, CardValue.TWO)
                        )
                    ),
                    Player(
                        "C", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.SIX),
                            Card(CardSuit.CLUBS, CardValue.NINE),
                            Card(CardSuit.SPADES, CardValue.ACE)
                        )
                    ),
                    Player(
                        "D", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.FIVE),
                            Card(CardSuit.CLUBS, CardValue.EIGHT),
                            Card(CardSuit.SPADES, CardValue.KING)
                        )
                    ),
                    Player(
                        "E", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.FOUR),
                            Card(CardSuit.CLUBS, CardValue.SEVEN),
                            Card(CardSuit.SPADES, CardValue.QUEEN)
                        )
                    )
                )
            )
        }
    }

    /***
     * Testet die Initialisierung mit ungültigen Testdaten
     */
    @Test
    fun initialize2() {
        // Test: initialize Game schlaegt fehl
        assertFailsWith<IllegalArgumentException> {
            val game = Game(
                listOf(
                    Player(
                        "A", arrayListOf(
                            Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                            Card(CardSuit.CLUBS, CardValue.JACK),
                            Card(CardSuit.SPADES, CardValue.THREE)
                        )
                    )
                )
            )
        }
    }

    /***
     * Testet die Initialisierung mit gültigen Testdaten
     */
    @Test
    fun initialize3() {
        // Test, ob es ein Game initialisieren kann
        val game = Game(
            listOf(
                Player(
                    "A", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.THREE)
                    )
                ),
                Player(
                    "B", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                        Card(CardSuit.CLUBS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.TWO)
                    )
                ),
                Player(
                    "C", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SIX),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.SPADES, CardValue.ACE)
                    )
                )
            )
        )
        // Test, ob die Anzahl von Spielern gueltig ist
        assertTrue(game.players.size in 2..4)
    }
    /***
     * Testet die nextPlayerTest-Methode mit gültigen Testdaten
     */
    @Test
    fun nextPlayerTest() {
        // Testdaten erzeugen
        val game = Game(
            listOf(
                Player(
                    "A", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.THREE)
                    )
                ),
                Player(
                    "B", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                        Card(CardSuit.CLUBS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.TWO)
                    )
                ),
                Player(
                    "C", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SIX),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.SPADES, CardValue.ACE)
                    )
                )
            )
        )
        var old = game.currentPlayer
        // Zu testende Methode aufrufen
        game.nextPlayer()
        // Test, ob es den aktuellen Spieler veraendert kann
        assertNotEquals(old, game.currentPlayer)
    }
    /***
     * Testet die nextPlayerTest-Methode mit gültigen Testdaten
     */
    @Test
    fun nextPlayerTest2() {
        // Testdaten erzeugen
        val game = Game(
            listOf(
                Player(
                    "A", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                        Card(CardSuit.CLUBS, CardValue.JACK),
                        Card(CardSuit.SPADES, CardValue.THREE)
                    )
                ),
                Player(
                    "B", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                        Card(CardSuit.CLUBS, CardValue.TEN),
                        Card(CardSuit.SPADES, CardValue.TWO)
                    )
                ),
                Player(
                    "C", arrayListOf(
                        Card(CardSuit.DIAMONDS, CardValue.SIX),
                        Card(CardSuit.CLUBS, CardValue.NINE),
                        Card(CardSuit.SPADES, CardValue.ACE)
                    )
                )
            )
        )
        var old = game.currentPlayer
        // Zu testende Methode aufrufen
        game.nextPlayer()
        game.nextPlayer()
        game.nextPlayer()
        // Test, ob es noch mal zu dem ersten Spieler kommt
        assertEquals(old, game.currentPlayer)
    }
}