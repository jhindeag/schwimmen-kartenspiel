package view

import entity.Player

/**
 * This interface provides the functions to change/update the UI
 * corresponding to the actions were made
 */
interface Refreshable {

    fun refreshAfterPlayerAdded() {

    }

    fun refreshAfterStartGame() {

    }

    fun refreshAfterPlayerStateChange(player: Player) {

    }

    fun refreshAfterPlacedCardsChange() {

    }

    fun refreshAfterEndGame() {

    }
}
