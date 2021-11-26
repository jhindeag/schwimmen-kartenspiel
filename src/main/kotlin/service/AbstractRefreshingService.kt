package service

import view.Refreshable

abstract class AbstractRefreshingService {

    private val refreshable = mutableListOf<Refreshable>()

    /**
     * adds a [Refreshable] to the list that gets called
     * whenever [onAllRefreshables] is used.
     */
    open fun addRefreshable(newRefreshable: Refreshable) {
        refreshable += newRefreshable
    }

    fun onAllRefreshables(method: Refreshable.() -> Unit) =
        refreshable.forEach { it.method() }

}