package view

import entity.Player
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.Alignment
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.Visual

/**
 * EndTurnScene is shown, when a player finished their turn
 * The next player will have to confirm before starting their turn
 */
class EndTurnScene(val rootService: RootService) : MenuScene(500, 500), Refreshable {
    private val nextPlayer = Label(
        width = 500,
        height = 500,
        text = "",
        font = Font(size = 40),
        alignment = Alignment.CENTER,
        visual = Visual.EMPTY
    )
    val confirm = Button(
        posX = 150,
        posY = 350,
        width = 200,
        height = 70,
        text = "CONFIRM",
        font = Font(size = 30),
        Alignment.CENTER,
        isWrapText = true,
        visual = ColorVisual.GREEN
    )

    init {
        opacity = 1.0
        background = ColorVisual(108, 168, 59)
        addComponents(nextPlayer, confirm)
    }

    override fun refreshAfterPlayerStateChange(player: Player) {
        nextPlayer.text = "Next player is ${player.name}"
    }
}