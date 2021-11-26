package view

import entity.CardImageLoader
import entity.Game
import entity.Player
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.event.MouseButtonType
import tools.aqua.bgw.event.MouseEvent
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import tools.aqua.bgw.visual.Visual
import java.awt.dnd.DragGestureEvent

class InGameScene(private val rootService: RootService) : BoardGameScene(1920, 1080), Refreshable {
    private val currentPlayer = Label(
        posX = 100,
        posY = 100,
        width = 500,
        height = 50,
        text = "Current player: ",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = Visual.EMPTY
    )
    private val drawPile = Label(
        posX = 200,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().backImage)
    )
    private val card1 = Button(
        posX = 600,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().blankImage)
    )
    private val card2 = Button(
        posX = 900,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().blankImage)
    )
    private val card3 = Button(
        posX = 1200,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().blankImage)
    )
    private val card1Hand = Button(
        posX = 800,
        posY = 630,
        width = 130,
        height = 200,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().blankImage)
    )
    private val card2Hand = Button(
        posX = 900,
        posY = 630,
        width = 130,
        height = 200,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ImageVisual(CardImageLoader().blankImage)
    )
    private val card3Hand = Button(
        posX = 1000,
        posY = 630,
        width = 130,
        height = 200,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual =    ImageVisual(CardImageLoader().blankImage)
    )
    private val pass = Button(
        posX = 1500,
        posY = 600,
        width = 200,
        height = 70,
        text = "PASS",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.RED
    )
    private val knock = Button(
        posX = 1500,
        posY = 700,
        width = 200,
        height = 70,
        text = "KNOCK",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.GREEN
    )
    private val takeAll = Button(
        posX = 1500,
        posY = 800,
        width = 200,
        height = 70,
        text = "TAKE ALL",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.ORANGE
    )

    init {
        background = ColorVisual(108, 168, 59)
        addComponents(
            currentPlayer, card1, card2, card3, drawPile,
            card1Hand, card2Hand, card3Hand, pass, knock, takeAll
        )
    }
}