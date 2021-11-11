package entity

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertFailsWith

internal class DrawPileTest {
    /***
     * Testet die Methode bei normalem Ziehstapel
     */
    @Test
    fun testdraw() {
        // Testdaten erzeugen
        val drawPile = DrawPile()
        var temp = Card(CardSuit.SPADES, CardValue.JACK)
        // Test, ob es erfolgreich eine Karte aus dem Stapel gezogen hat
        assertDoesNotThrow { drawPile.draw() }
    }

    /**
     * Testet die Methode bei leerem Ziehstapel
     */
    @Test
    fun testdraw2() {
        // Testdaten erzeugen
        val drawPile = DrawPile()
        var temp = Card(CardSuit.SPADES, CardValue.JACK)
        // 32 Karten aus dem Ziehstapel ziehen, sodass es ein leerer Stapel ist
        for (i in 0..31) {
            temp = drawPile.draw()
        }
        // Test: draw schlaegt fehl
        assertFailsWith<IllegalStateException> {
            // Zu testende Methode bei ungültigem Zustand aufrufen
            temp = drawPile.draw()
        }
    }


}