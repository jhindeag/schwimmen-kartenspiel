package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class EndGameScene(val rootService: RootService) : BoardGameScene(500, 500), Refreshable {

    private val winnerLabel = Label(
        width = 500,
        height = 40,
        posX = 0,
        posY = 50,
        text = "is the WINNER!",
        font = Font(size = 30)
    )

    private val player1 = Label(
        posX = 50,
        posY = 150,
        width = 180,
        height = 50,
        text = "Alex",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player2 = Label(
        posX = 50,
        posY = 200,
        width = 180,
        height = 50,
        text = "Bertha",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player3 = Label(
        posX = 50,
        posY = 250,
        width = 180,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val player4 = Label(
        posX = 50,
        posY = 300,
        width = 180,
        height = 50,
        text = "",
        font = Font(size = 20),
        alignment = Alignment.CENTER_LEFT
    )

    private val newGame = Button(
        posX = 35,
        posY = 420,
        width = 180,
        height = 50,
        text = "NEW GAME",
        font = Font(size = 20),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.GREEN
    ).apply {
        onMouseClicked = {
            super.refreshAfterEndGame()
        }
    }

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
        addComponents(winnerLabel, player1, player2, player3, player4, newGame, quitGame)
    }
}