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
                    Player("A"),
                    Player("B"),
                    Player("C"),
                    Player("D"),
                    Player("E")
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
                    Player("A")
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
                Player("A"),
                Player("B"),
                Player("C")
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
                Player("A"),
                Player("B"),
                Player("C")
            )
        )
        val old = game.currentPlayer
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
                Player("A"),
                Player("B"),
                Player("C")
            )
        )
        val old = game.currentPlayer
        // Zu testende Methode aufrufen
        game.nextPlayer()
        game.nextPlayer()
        game.nextPlayer()
        // Test, ob es noch mal zu dem ersten Spieler kommt
        assertEquals(old, game.currentPlayer)
    }
}