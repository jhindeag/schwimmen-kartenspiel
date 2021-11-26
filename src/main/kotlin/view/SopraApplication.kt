package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

class SopraApplication : BoardGameApplication("Schwimmen") {
    private val rootService = RootService()
    private val helloScene = HelloScene(rootService)

    init {
        this.showGameScene(helloScene)
    }

}

