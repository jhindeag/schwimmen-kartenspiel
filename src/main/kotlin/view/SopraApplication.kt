package view

import entity.Player
import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

class SopraApplication : BoardGameApplication("Schwimmen"), Refreshable {
    private val rootService = RootService()
    private val mainMenuScene = MainMenuScene(rootService)
    private val inGameScene = InGameScene(rootService)
    private val scoreboardScene = ScoreboardScene(rootService).apply {
        newGame.onMouseClicked = { showMenuScene(mainMenuScene) }
    }
    private val endTurnScene = EndTurnScene(rootService).apply {
        confirm.onMouseClicked = { hideMenuScene(500) }
    }

    init {
        rootService.addRefreshables(
            this,
            mainMenuScene,
            inGameScene,
            scoreboardScene,
            endTurnScene
        )
        this.showGameScene(inGameScene)
        this.showMenuScene(mainMenuScene, 500)
    }

    override fun refreshAfterStartGame() {
        this.hideMenuScene()
    }

    override fun refreshAfterEndGame() {
        this.hideMenuScene()
        this.showMenuScene(scoreboardScene, 500)
    }

    override fun refreshAfterPlayerStateChange(player: Player) {
        if (rootService.currentGame != null) {
            this.showMenuScene(endTurnScene)
        }
    }
}

