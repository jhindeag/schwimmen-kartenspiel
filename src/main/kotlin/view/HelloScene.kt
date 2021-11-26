package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.event.MouseButtonType
import tools.aqua.bgw.event.MouseEvent
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

class HelloScene(val rootService: RootService) : BoardGameScene(500, 500), Refreshable {

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
        prompt = "Alex",
        font = Font(size = 20)
    )

    private val player2 = TextField(
        posX = 270,
        posY = 150,
        width = 180,
        height = 50,
        text = "Bertha",
        prompt = "Bertha",
        font = Font(size = 20)
    )

    private val player3 = TextField(
        posX = 50,
        posY = 250,
        width = 180,
        height = 50,
        text = "",
        prompt = "",
        font = Font(size = 20)
    )

    private val player4 = TextField(
        posX = 270,
        posY = 250,
        width = 180,
        height = 50,
        text = "",
        prompt = "",
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
       // onMouseClicked(MouseEvent(MouseButtonType.LEFT_BUTTON,))
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
    )

    init {
        background = ColorVisual(108, 168, 59)
        addComponents(helloLabel, player1, player2, player3, player4, startGame, quitGame)
    }

}