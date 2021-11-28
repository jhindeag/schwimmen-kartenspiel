package view

import entity.Card
import entity.CardImageLoader
import entity.DrawPile
import entity.Player
import service.RootService
import tools.aqua.bgw.components.gamecomponentviews.CardView
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.BidirectionalMap
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import tools.aqua.bgw.visual.Visual

class InGameScene(private val rootService: RootService) :
    BoardGameScene(1920, 1080), Refreshable {
    private val currentPlayer = Label(
        posX = 100,
        posY = 100,
        width = 500,
        height = 50,
        text = "",
        font = Font(size = 30),
        Alignment.CENTER_LEFT,
        isWrapText = true,
        visual = Visual.EMPTY
    )
    private val drawPile = CardView(
        posX = 200,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var table1 = CardView(
        posX = 600,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var table2 = CardView(
        posX = 900,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var table3 = CardView(
        posX = 1200,
        posY = 200,
        width = 130 * 1.5,
        height = 200 * 1.5,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var hand1 = CardView(
        posX = 800,
        posY = 630,
        width = 130,
        height = 200,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var hand2 = CardView(
        posX = 900,
        posY = 630,
        width = 130,
        height = 200,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
    )
    private var hand3 = CardView(
        posX = 1000,
        posY = 630,
        width = 130,
        height = 200,
        front = ImageVisual(CardImageLoader().blankImage),
        back = ImageVisual(CardImageLoader().backImage)
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
    ).apply {
        onMouseClicked = {
            rootService.actionService.pass()
        }
    }
    private val cardMap: BidirectionalMap<Card, CardView> = BidirectionalMap()
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
    ).apply {
        onMouseClicked = {
            rootService.actionService.knock()
        }
    }
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
    ).apply {
        onMouseClicked = {
            rootService.actionService.takeAll()
        }
    }

    init {
        background = ColorVisual(108, 168, 59)
        addComponents(
            drawPile,
            pass,
            knock,
            takeAll
        )
    }

    override fun refreshAfterStartGame() {
        clearComponents()
        val game = rootService.currentGame
        checkNotNull(game)
        addComponents(
            drawPile,
            table1,
            table2,
            table3,
            pass,
            knock,
            takeAll
        )
        refreshAfterPlayerStateChange(game.currentPlayer)
    }

    override fun refreshAfterPlacedCardsChange() {
        val game = rootService.currentGame
        checkNotNull(game)
        val cardImageLoader = CardImageLoader()
        initializeCardMap(cardImageLoader)

        var temp = game.placedCards[0]
        checkNotNull(temp)
        table1 = cardMap.forward(temp)

        temp = game.placedCards[1]
        checkNotNull(temp)
        table2 = cardMap.forward(temp)

        temp = game.placedCards[2]
        checkNotNull(temp)
        table3 = cardMap.forward(temp)

        table1.showFront()
        table1.resize(130 * 1.5, 200 * 1.5)
        table1.reposition(
            posX = 600,
            posY = 200
        )

        table2.showFront()
        table2.resize(130 * 1.5, 200 * 1.5)
        table2.reposition(
            posX = 900,
            posY = 200,
        )

        table3.showFront()
        table3.resize(130 * 1.5, 200 * 1.5)
        table3.reposition(
            posX = 1200,
            posY = 200,
        )

        table1.isDraggable = true
        table2.isDraggable = true
        table3.isDraggable = true

        table1.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                hand1, hand2, hand3 -> true
                else -> false
            }
        }
        table2.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                hand1, hand2, hand3 -> true
                else -> false
            }
        }
        table3.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                hand1, hand2, hand3 -> true
                else -> false
            }
        }

        table1.onDragDropped = { dragEvent ->
            when (dragEvent.draggedComponent) {
                hand1 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[0]),
                    checkNotNull(game.placedCards[0])
                )
                hand2 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[1]),
                    checkNotNull(game.placedCards[0])
                )
                hand3 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[2]),
                    checkNotNull(game.placedCards[0])
                )
            }
        }

        table2.onDragDropped = { dragEvent ->
            when (dragEvent.draggedComponent) {
                hand1 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[0]),
                    checkNotNull(game.placedCards[1])
                )
                hand2 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[1]),
                    checkNotNull(game.placedCards[1])
                )
                hand3 -> rootService.actionService.tradeOne(
                    checkNotNull(game.currentPlayer.hand[2]),
                    checkNotNull(game.placedCards[1])
                )
            }
        }

        table3.onDragDropped = { dragEvent ->
            run {
                when (dragEvent.draggedComponent) {
                    hand1 -> rootService.actionService.tradeOne(
                        checkNotNull(game.currentPlayer.hand[0]),
                        checkNotNull(game.placedCards[2])
                    )
                    hand2 -> rootService.actionService.tradeOne(
                        checkNotNull(game.currentPlayer.hand[1]),
                        checkNotNull(game.placedCards[2])
                    )
                    hand3 -> rootService.actionService.tradeOne(
                        checkNotNull(game.currentPlayer.hand[2]),
                        checkNotNull(game.placedCards[2])
                    )
                }
            }
        }
        removeComponents(
            table1,
            table2,
            table3
        )
        addComponents(
            table1,
            table2,
            table3
        )
    }

    override fun refreshAfterPlayerStateChange(player: Player) {
        val game = rootService.currentGame
        checkNotNull(game)
        val cardImageLoader = CardImageLoader()
        initializeCardMap(cardImageLoader)

        var temp = player.hand[0]
        checkNotNull(temp)
        hand1 = cardMap.forward(temp)

        temp = player.hand[1]
        checkNotNull(temp)
        hand2 = cardMap.forward(temp)

        temp = player.hand[2]
        checkNotNull(temp)
        hand3 = cardMap.forward(temp)

        hand1.showFront()
        hand1.resize(130, 200)
        hand1.reposition(
            posX = 800,
            posY = 630
        )

        hand2.showFront()
        hand2.resize(130, 200)
        hand2.reposition(
            posX = 900,
            posY = 630,
        )

        hand3.showFront()
        hand3.resize(130, 200)
        hand3.reposition(
            posX = 1000,
            posY = 630,
        )

        hand1.isDraggable = true
        hand2.isDraggable = true
        hand3.isDraggable = true
        currentPlayer.text = "Current player: ${player.name}"
        hand1.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1, table2, table3 -> true
                else -> false
            }
        }
        hand2.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1, table2, table3 -> true
                else -> false
            }
        }
        hand3.dropAcceptor = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1, table2, table3 -> true
                else -> false
            }
        }

        hand1.onDragDropped = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[0]),
                    checkNotNull(game.placedCards[0])
                )
                table2 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[0]),
                    checkNotNull(game.placedCards[1])
                )
                table3 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[0]),
                    checkNotNull(game.placedCards[2])
                )
            }
        }

        hand2.onDragDropped = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[1]),
                    checkNotNull(game.placedCards[0])
                )
                table2 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[1]),
                    checkNotNull(game.placedCards[1])
                )
                table3 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[1]),
                    checkNotNull(game.placedCards[2])
                )
            }
        }

        hand3.onDragDropped = { dragEvent ->
            when (dragEvent.draggedComponent) {
                table1 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[2]),
                    checkNotNull(game.placedCards[0])
                )
                table2 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[2]),
                    checkNotNull(game.placedCards[1])
                )
                table3 -> rootService.actionService.tradeOne(
                    checkNotNull(player.hand[2]),
                    checkNotNull(game.placedCards[2])
                )
            }
        }

        removeComponents(
            hand1,
            hand2,
            hand3,
            currentPlayer
        )
        addComponents(
            hand1,
            hand2,
            hand3,
            currentPlayer
        )
    }

    override fun refreshAfterEndGame() {
        clearComponents()
    }

    private fun initializeCardMap(cardImageLoader: CardImageLoader) {
        val drawPile = DrawPile()
        drawPile.remainingCards.peekAll().forEach { card ->
            val cardView = CardView(
                height = 200,
                width = 130,
                front = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue)),
                back = ImageVisual(cardImageLoader.backImage)
            )
            cardMap.add(card to cardView)
        }
    }
}