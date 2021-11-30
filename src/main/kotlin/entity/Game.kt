package entity

/**
 * this class has all the information needed for a game
 * to be initialized and to progress
 *
 * @param players list of players who take part in the game
 */
class Game(var players:List<Player>) {
    var passCount = 0
    var currentPlayer: Player = players[0]
    val drawPile = DrawPile()
    var placedCards = arrayListOf<Card?>(null, null, null)
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
        if (!ite.hasNext()){
            ite = players.iterator()
        }
        currentPlayer = ite.next()
    }
}