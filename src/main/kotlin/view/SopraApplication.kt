package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

class SopraApplication : BoardGameApplication("Schwimmen"), Refreshable {
    private val rootService = RootService()
    private val mainMenuScene = MainMenuScene(rootService)
    private val inGameScene = InGameScene(rootService)
    private val scoreboardScene = ScoreboardScene(rootService).apply {
        newGame.onMouseClicked = { showMenuScene(mainMenuScene) }
    }

    init {
        rootService.addRefreshables(
            this,
            mainMenuScene,
            inGameScene,
            scoreboardScene
        )
        this.showGameScene(inGameScene)
        this.showMenuScene(mainMenuScene, 500)
    }

    override fun refreshAfterStartGame() {
        this.hideMenuScene()
    }

    override fun refreshAfterEndGame() {
        this.showMenuScene(scoreboardScene, 500)
    }
}

