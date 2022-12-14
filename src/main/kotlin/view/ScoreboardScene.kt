package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

/**
 * ScoreboardScene is shown at the end of the game
 * it can be seen, who has won the game and
 * also the current points of the other players
 */
class ScoreboardScene(val rootService: RootService) : MenuScene(500, 500), Refreshable {

    private val winnerLabel = Label(
        width = 500,
        height = 40,
        posX = 0,
        posY = 50,
        font = Font(size = 30)
    )

    private val points = Label(
        posX = 350,
        posY = 100,
        width = 300,
        height = 50,
        text = "Points",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val p1Points = Label(
        posX = 350,
        posY = 150,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val p2Points = Label(
        posX = 350,
        posY = 200,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val p3Points = Label(
        posX = 350,
        posY = 250,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val p4Points = Label(
        posX = 350,
        posY = 300,
        width = 100,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player1 = Label(
        posX = 50,
        posY = 150,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player2 = Label(
        posX = 50,
        posY = 200,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player3 = Label(
        posX = 50,
        posY = 250,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player4 = Label(
        posX = 50,
        posY = 300,
        width = 300,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    val newGame = Button(
        posX = 35,
        posY = 420,
        width = 180,
        height = 50,
        text = "NEW GAME",
        font = Font(size = 20),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.GREEN
    )

    private val quitGame = Button(
        posX = 285,
        posY = 420,
        width = 180,
        height = 50,
        text = "QUIT",
        font = Font(size = 20),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.RED
    ).apply {
        onMouseClicked = {
            rootService.mainMenuService.quitGame()
        }
    }

    init {
        opacity = 0.5
        background = ColorVisual(108, 168, 59)
        addComponents(
            winnerLabel,
            player1,
            player2,
            player3,
            player4,
            points,
            p1Points,
            p2Points,
            p3Points,
            p4Points,
            newGame,
            quitGame
        )
    }
    //it is calculated here, who are the winners and the scoreboard will be
    //shown corresponding to the scores
    override fun refreshAfterEndGame() {
        val game = rootService.currentGame
        checkNotNull(game)
        var winners = game.players[0].name
        var winnersCounter = 1
        for (i in 1 until game.players.size) {
            if (game.players[i].points == game.players[0].points) {
                winners += ", ${game.players[i].name}"
                winnersCounter++
            }
        }
        when (winnersCounter) {
            1 -> {
                winnerLabel.text = "$winners is the WINNER!"
            }
            game.players.size -> {
                winnerLabel.text = "Round DRAW!"
            }
            else -> {
                winnerLabel.text = "$winners are the WINNERS!"
            }
        }
        player1.text = "${game.players[0]}:"
        p1Points.text = "${game.players[0].points}"
        player2.text = "${game.players[1]}:"
        p2Points.text = "${game.players[1].points}"
        player3.text = ""
        p3Points.text = ""
        player4.text = ""
        p4Points.text = ""
        if (game.players.size == 3) {
            player3.text = "${game.players[2]}:"
            p3Points.text = "${game.players[2].points}"
        }
        if (game.players.size == 4) {
            player3.text = "${game.players[2]}:"
            p3Points.text = "${game.players[2].points}"
            player4.text = "${game.players[3]}:"
            p4Points.text = "${game.players[3].points}"
        }
    }
}