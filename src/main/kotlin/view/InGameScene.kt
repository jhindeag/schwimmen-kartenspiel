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
    private var table = ArrayList<CardView>(3)
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
    private var hand = ArrayList<CardView>(3)
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
    private val cardMap: BidirectionalMap<Card, CardView> = BidirectionalMap()
    private val readyToTrade: Boolean
        get() = readyHand && readyTable
    private var readyHand = false
    private var handToTrade = -1
    private var readyTable = false
    private var tableToTrade = -1
    val actions = ArrayList<Button>(3)
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
            currentPlayer,
            hand1,
            hand2,
            hand3,
            table1,
            table2,
            table3,
            pass,
            knock,
            takeAll
        )
    }

    override fun refreshAfterStartGame() {
        val game = rootService.currentGame
        checkNotNull(game)
        clearComponents()
        addComponents(
            drawPile,
            currentPlayer,
            hand1,
            hand2,
            hand3,
            table1,
            table2,
            table3,
            pass,
            knock,
            takeAll
        )
        actions.addAll(listOf(pass, knock, takeAll))
    }

    override fun refreshAfterPlacedCardsChange() {
        val game = rootService.currentGame
        checkNotNull(game)
        val cardImageLoader = CardImageLoader()
        initializeCardMap(cardImageLoader)

        var temp = game.placedCards[0]
        checkNotNull(temp)
        table1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        temp = game.placedCards[1]
        checkNotNull(temp)
        table2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        temp = game.placedCards[2]
        checkNotNull(temp)
        table3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        table.clear()
        table.addAll(listOf(table1, table2, table3))

        for (i in 0..table.size - 1) {
            table[i].showFront()
            table[i].resize(130 * 1.5, 200 * 1.5)
            table[i].reposition(
                posX = 600 + 300 * i,
                posY = 200
            )
            table[i].isDraggable = true
            table[i].dropAcceptor = { dragEvent ->
                when (dragEvent.draggedComponent) {
                    hand1, hand2, hand3 -> true
                    else -> false
                }
            }
            table[i].onDragDropped = { dragEvent ->
                rootService.actionService.tradeOne(
                    checkNotNull(
                        game.currentPlayer.hand[
                                hand.indexOf(dragEvent.draggedComponent)]
                    ),
                    checkNotNull(game.placedCards[i])
                )
            }
            table[i].apply {
                onMouseClicked = {
                    readyTable = true
                    tableToTrade = i
                    table.forEach {
                        it.reposition(posY = 200, posX = it.posX)
                    }
                    table[i].reposition(posY = 150, posX = table[i].posX)
                    if (readyToTrade) {
                        readyTable = false
                        readyHand = false
                        rootService.actionService.tradeOne(
                            checkNotNull(game.currentPlayer.hand[handToTrade]),
                            checkNotNull(game.placedCards[tableToTrade])
                        )
                    }
                }
            }
        }
    }


    override fun refreshAfterPlayerStateChange(player: Player) {
        val game = rootService.currentGame
        checkNotNull(game)
        val cardImageLoader = CardImageLoader()
        initializeCardMap(cardImageLoader)

        var temp = player.hand[0]
        checkNotNull(temp)
        hand1.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        temp = player.hand[1]
        checkNotNull(temp)
        hand2.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        temp = player.hand[2]
        checkNotNull(temp)
        hand3.frontVisual = ImageVisual(cardImageLoader.frontImageFor(temp.cardSuit, temp.cardValue))

        hand.clear()
        hand.addAll(listOf(hand1, hand2, hand3))

        for (i in 0..hand.size - 1) {
            hand[i].showFront()
            hand[i].resize(130, 200)
            hand[i].reposition(
                posX = 800 + 100 * i,
                posY = 630
            )
            hand[i].isDraggable = true
            hand[i].dropAcceptor = { dragEvent ->
                when (dragEvent.draggedComponent) {
                    table1, table2, table3 -> true
                    else -> false
                }
            }
            hand[i].onDragDropped = { dragEvent ->
                rootService.actionService.tradeOne(
                    checkNotNull(
                        game.currentPlayer.hand[i]
                    ),
                    checkNotNull(game.placedCards[table.indexOf(dragEvent.draggedComponent)])
                )
            }
            hand[i].apply {
                onMouseClicked = {
                    readyHand = true
                    handToTrade = i
                    hand.forEach {
                        it.reposition(posY = 630, posX = it.posX)
                    }
                    hand[i].reposition(posY = 600, posX = hand[i].posX)
                    if (readyToTrade) {
                        readyTable = false
                        readyHand = false
                        rootService.actionService.tradeOne(
                            checkNotNull(player.hand[handToTrade]),
                            checkNotNull(game.placedCards[tableToTrade])
                        )
                    }
                }
            }
        }
        currentPlayer.text = "Current player: ${player.name}"
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