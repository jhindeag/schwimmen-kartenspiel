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
        // 32 Karten aus dem Ziehstapel ziehen, sodass es ein leerer Stapel ist
        for (i in 0..31) {
            drawPile.draw()
        }
        // Test: draw schlaegt fehl
        assertFailsWith<IllegalStateException> {
            // Zu testende Methode bei ungültigem Zustand aufrufen
            drawPile.draw()
        }
    }


}