package service

import entity.Game
import entity.Player
import view.SopraApplication

class MainMenuService {
    val gameServ = GameService()
    fun startGame(p1: String, p2: String, p3: String?, p4: String?) {
        if (p4 == null && p3 != null) {
            gameServ.currentGame = Game(
                listOf(
                    Player(p1, arrayListOf(null, null, null)),
                    Player(p2, arrayListOf(null, null, null)),
                    Player(p3, arrayListOf(null, null, null))
                )
            )
        }
        if (p4 != null && p3 == null) {
            gameServ.currentGame = Game(
                listOf(
                    Player(p1, arrayListOf(null, null, null)),
                    Player(p2, arrayListOf(null, null, null)),
                    Player(p4, arrayListOf(null, null, null))
                )
            )
        } else if (p3 == null && p4 == null) {
            gameServ.currentGame = Game(
                listOf(
                    Player(p1, arrayListOf(null, null, null)),
                    Player(p2, arrayListOf(null, null, null))
                )
            )
        } else if (p3 != null && p4 != null) {
            gameServ.currentGame = Game(
                listOf(
                    Player(p1, arrayListOf(null, null, null)),
                    Player(p2, arrayListOf(null, null, null)),
                    Player(p3, arrayListOf(null, null, null)),
                    Player(p4, arrayListOf(null, null, null))
                )
            )
        }
    }

    fun quitGame() {
        SopraApplication().exit()
    }
}