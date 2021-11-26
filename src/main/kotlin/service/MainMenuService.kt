package service

import entity.Game
import entity.Player
import view.SopraApplication
import kotlin.system.exitProcess

/***
 * This class defines the logic for the
 * actions on the main menu of the game
 */
class MainMenuService(private val rootService: RootService) : AbstractRefreshingService() {

    /***
     * Create a new game with the give names of the players
     *
     * @param p1 name of the player 1
     * @param p2 name of the player 2
     * @param p3 name of the player 3
     * @param p4 name of the player 4
     */
    fun startGame(p1: String, p2: String, p3: String?, p4: String?) {
        var game: Game? = null
        if (p4 == "" && p3 != "") {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p3.toString())
                )
            )
        } else if (p4 != "" && p3 == "") {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p4.toString())
                )
            )
        } else if (p3 != "" && p4 != "") {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p3.toString()),
                    Player(p4.toString())
                )
            )
        } else {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2)
                )
            )
            rootService.currentGame = game
            rootService.gameService.handOutCards()
            onAllRefreshables { refreshAfterStartGame() }
        }

        /***
         * Closes the application
         */
        fun quitGame() {
            SopraApplication().exit()
            exitProcess(0)
        }
    }
}