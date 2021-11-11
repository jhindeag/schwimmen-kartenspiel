package entity

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PlayerTest {
    /**
     * Testet die Methode mit gültigen Karten
     */
    @Test
    fun testToString() {
        //Testdaten erzeugen
        val player1 = Player("Richard", arrayListOf(Card(CardSuit.DIAMONDS,CardValue.EIGHT),Card(CardSuit.CLUBS,CardValue.JACK),Card(CardSuit.SPADES,CardValue.THREE)))
        //Test, ob es die Karten auf Hand richtig veranschaulicht hat
        assertEquals("${player1.hand[0]} ${player1.hand[1]} ${player1.hand[2]}",player1.toString())
    }
}