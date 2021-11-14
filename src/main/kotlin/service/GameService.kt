package service

import entity.Game
import entity.Player

class GameService {
    var currentGame = Game(
        listOf(
            Player("", arrayListOf(null, null, null)),
            Player("", arrayListOf(null, null, null))
        )
    )

    fun handOutCards() {
        for (i in 0 until currentGame.players.size) {
            currentGame.players[i].hand =
                arrayListOf(
                    currentGame.drawPile.draw(),
                    currentGame.drawPile.draw(),
                    currentGame.drawPile.draw()
                )
        }
    }

    fun resetPlacedCards() {
        currentGame.placedCards = arrayListOf(
            currentGame.drawPile.draw(),
            currentGame.drawPile.draw(),
            currentGame.drawPile.draw()
        )
    }

    fun afterPlayerTurn(): Boolean {
        currentGame.nextPlayer()
        return currentGame.currentPlayer.hasKnocked
    }

    fun endGame() {
        currentGame.players.sortedWith(compareBy { it.points })
        println("${currentGame.players[0]} has gewonnen!")
        for (i in 0 until currentGame.players.size){
            println("\t${currentGame.players[i]}     \t${currentGame.players[i].points}")
        }
    }
}