package entity

class Game(var players:List<Player>) {
    var passCount = 0
    var currentPlayer: Player = players[0]
    val drawPile = DrawPile()
    var placedCards = arrayOfNulls<Card>(3)
    var ite = players.iterator()

    /**
     * Die Methode stellt fest, dass es genug Spieler gibt, um das Spiel zu starten.
     * Wenn es schon genug Spieler gibt, wird die Reihenfolge von Spielern gemischt.
     */
    init {
        if (players.size < 2) {
            throw IllegalArgumentException("Es gibt zu wenig Spieler")
        } else if (players.size > 4) {
            throw IllegalArgumentException("Es gibt zu viele Spieler")
        }
        players = players.shuffled()
        ite = players.iterator()
        currentPlayer = ite.next()
    }

    /**
     * Die Methode aktualisiert den aktuellen Spieler, der als nächstes dran ist
     */
    fun nextPlayer() {
        if (ite.hasNext()) {
            currentPlayer = ite.next()
        } else {
            ite = players.iterator()
            currentPlayer = players[0]
        }
    }
}