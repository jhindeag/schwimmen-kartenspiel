package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

class SopraApplication : BoardGameApplication("Schwimmen"), Refreshable {
    private val rootService = RootService()
    private val mainMenuScene = MainMenuScene(rootService)
    private val inGameScene = InGameScene(rootService)
    private val endGameScene = EndGameScene(rootService)

    init {
        rootService.addRefreshables(
            this,
            mainMenuScene,
            inGameScene,
            endGameScene
        )
     // this.showGameScene(mainMenuScene)
       //this.showGameScene(inGameScene)
       this.showGameScene(endGameScene)
    }

    override fun refreshAfterEndGame() {
        this.showGameScene(mainMenuScene)
    }
}

