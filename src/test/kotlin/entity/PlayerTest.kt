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
}