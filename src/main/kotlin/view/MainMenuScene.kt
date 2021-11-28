package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class MainMenuScene(val rootService: RootService) : MenuScene(500, 500, ColorVisual.WHITE), Refreshable {

    private val helloLabel = Label(
        width = 500,
        height = 500,
        posX = 0,
        posY = -200,
        text = "SCHWIMMEN",
        font = Font(size = 50)
    )

    private val player1 = TextField(
        posX = 50,
        posY = 150,
        width = 180,
        height = 50,
        text = "Alex",
        prompt = "Player 1",
        font = Font(size = 20)
    )

    private val player2 = TextField(
        posX = 270,
        posY = 150,
        width = 180,
        height = 50,
        text = "Bertha",
        prompt = "Player 2",
        font = Font(size = 20)
    )

    private val player3 = TextField(
        posX = 50,
        posY = 250,
        width = 180,
        height = 50,
        text = "",
        prompt = "Player 3",
        font = Font(size = 20)
    )

    private val player4 = TextField(
        posX = 270,
        posY = 250,
        width = 180,
        height = 50,
        text = "",
        prompt = "Player 4",
        font = Font(size = 20)
    )

    private val startGame = Button(
        posX = 150,
        posY = 350,
        width = 200,
        height = 70,
        text = "START",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.GREEN
    ).apply {
        onMouseClicked = {
            rootService.currentGame = null
            if (player3.text == "") {
                if (player4.text == "") {
                    rootService.mainMenuService.startGame(
                        player1.text, player2.text, null, null
                    )
                } else {
                    rootService.mainMenuService.startGame(
                        player1.text, player2.text, null, player4.text
                    )
                }
            } else if (player4.text == "") {
                rootService.mainMenuService.startGame(
                    player1.text, player2.text, player3.text, null
                )
            } else {
                rootService.mainMenuService.startGame(
                    player1.text, player2.text, player3.text, player4.text
                )
            }
        }
    }

    private val quitGame = Button(
        posX = 160,
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
        background = ColorVisual(108, 168, 59)
        addComponents(helloLabel, player1, player2, player3, player4, startGame, quitGame)
    }

}