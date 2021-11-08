package entity

class Game() {
    var passCount = 0
    val players = arrayOfNulls<Player>(4)
    var currentPlayer: Player? = Player("", 0, false)
    val drawPile = DrawPile()
    var placedCards = arrayOfNulls<Card>(3)
    var it = players.iterator()
    fun nextPlayer() {
        when (it.hasNext()) {
            true -> currentPlayer = it.next()
            else -> it = players.iterator()
        }
    }
}