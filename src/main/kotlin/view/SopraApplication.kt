package view

import entity.Player
import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

/**
 * this class manipulates the scenes according to the actions
 * were taken in-game
 */
class SopraApplication : BoardGameApplication("Schwimmen"), Refreshable {
    private val rootService = RootService()
    private val mainMenuScene = MainMenuScene(rootService)
    private val inGameScene = InGameScene(rootService)
    private val endTurnScene = EndTurnScene(rootService).apply {
        confirm.onMouseClicked = {
            hideMenuScene(500)
            inGameScene.actions.forEach {
                it.isDisabled = false
            }
        }
    }

    private val scoreboardScene = ScoreboardScene(rootService).apply {
        newGame.onMouseClicked = { showMenuScene(mainMenuScene) }
    }

    init {
        rootService.addRefreshables(
            this,
            mainMenuScene,
            inGameScene,
            endTurnScene,
            scoreboardScene
        )
        this.showGameScene(inGameScene)
        this.showMenuScene(mainMenuScene, 500)
    }

    override fun refreshAfterStartGame() {
        this.hideMenuScene()
        inGameScene.actions.forEach {
            it.isDisabled = false
        }
    }

    override fun refreshAfterEndGame() {
        this.hideMenuScene()
        this.showMenuScene(scoreboardScene, 500)
    }

    override fun refreshAfterPlayerStateChange(player: Player) {
        if (rootService.currentGame != null) {
            inGameScene.actions.forEach {
                it.isDisabled = true
            }
            this.showMenuScene(endTurnScene)
        }
    }
}

