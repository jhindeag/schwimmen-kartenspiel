package service

import entity.Game
import entity.Player
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
        val game: Game?
        if (p4 == null && p3 != null) {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p3)
                )
            )
        } else if (p4 != null && p3 == null) {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p4)
                )
            )
        } else if (p3 != null && p4 != null) {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2),
                    Player(p3),
                    Player(p4)
                )
            )
        } else {
            game = Game(
                listOf(
                    Player(p1),
                    Player(p2)
                )
            )
        }
        rootService.currentGame = game
        rootService.gameService.handOutCards()
        rootService.gameService.resetPlacedCards()
        onAllRefreshables { refreshAfterStartGame() }
    }

    /***
     * Closes the application
     */
    fun quitGame() {
        exitProcess(0)
    }
}
