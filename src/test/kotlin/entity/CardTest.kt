package entity

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CardTest {

    /**
     * Testes die Methode von zwei gültigen Karten
     */
    @Test
    fun testToString() {
        // Testkarte erzeugen
        val card1 = Card(CardSuit.SPADES,CardValue.SEVEN)
        // Test, ob es die Karte richtig veranschaulicht hat
        assertEquals("7♠",card1.toString())
    }
    @Test
    fun testToString2() {
        // Testkarte erzeugen
        val card2 = Card(CardSuit.HEARTS,CardValue.KING)
        // Test, ob es die Karte richtig veranschaulicht hat
        assertEquals("K♥",card2.toString())
    }
}