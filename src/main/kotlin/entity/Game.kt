package entity

class Game(var passCount: Int) {
    val players = arrayOfNulls<Player>(4)
    var currentPlayer = Player("", 0, false)
    val drawPile = DrawPile()
    var placedCards = arrayOfNulls<Card>(3)
}