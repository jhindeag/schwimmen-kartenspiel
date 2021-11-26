package entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PlayerTest {
    /**
     * Testet die Methode mit gültigen Karten
     */
    @Test
    fun testToString() {
        //Testdaten erzeugen
        val player1 = Player("Richard")
        //Test, ob es den Namen richtig veranschaulicht hat
        assertEquals("Richard", player1.toString())
    }

    /***
     * Test the function calculate
     */
    @Test
    fun testCalculate() {
        val player = Player("Richard")
        player.hand = arrayListOf(
            Card(CardSuit.DIAMONDS, CardValue.ACE),
            Card(CardSuit.DIAMONDS, CardValue.KING),
            Card(CardSuit.DIAMONDS, CardValue.QUEEN)
        )
        player.points = player.calculate()
        assertEquals(31.0, player.points)
    }

    /***
     * Test the function calculate
     */
    @Test
    fun testCalculate2() {
        val player = Player("Richard")
        player.hand = arrayListOf(
            Card(CardSuit.DIAMONDS, CardValue.ACE),
            Card(CardSuit.SPADES, CardValue.ACE),
            Card(CardSuit.HEARTS, CardValue.ACE)
        )
        player.points = player.calculate()
        assertEquals(30.5, player.points)
    }

    /***
     * Test the function calculate
     */
    @Test
    fun testCalculate3() {
        val player = Player("Richard")
        player.hand = arrayListOf(
            Card(CardSuit.HEARTS, CardValue.ACE),
            Card(CardSuit.DIAMONDS, CardValue.KING),
            Card(CardSuit.DIAMONDS, CardValue.SEVEN)
        )
        player.points = player.calculate()
        assertEquals(17.0, player.points)
    }
}