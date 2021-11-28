package entity

import kotlin.math.max

/***
 * This class defines all information that each player has
 */
data class Player(val name: String) {
    var hand = ArrayList<Card?>(3)
    val points : Double
        get() = calculate()
    var hasKnocked = false

    /**
     * Die Methode überprüft, ob der Spieler einen gültigen Namen eingegeben hat
     * ein ungültiger Name wäre ein leerer String
     */

    fun calculate(): Double {
        var maxClubs = 0.0
        var maxSpades = 0.0
        var maxHearts = 0.0
        var maxDiamonds = 0.0
        if ((checkNotNull(hand[0]).cardValue == checkNotNull(hand[1]).cardValue)
            && (checkNotNull(hand[0]).cardValue == checkNotNull(hand[2]).cardValue)
        ) {
            return 30.5
        } else {
            for (i in 0..2) {
                when (checkNotNull(hand[i]).cardSuit) {
                    CardSuit.CLUBS -> maxClubs += checkNotNull(hand[i]).cardValue.toInt()
                    CardSuit.SPADES -> maxSpades += checkNotNull(hand[i]).cardValue.toInt()
                    CardSuit.HEARTS -> maxHearts += checkNotNull(hand[i]).cardValue.toInt()
                    CardSuit.DIAMONDS -> maxDiamonds += checkNotNull(hand[i]).cardValue.toInt()
                }
            }
            return max(
                max(
                    maxClubs,
                    maxSpades
                ), max(
                    maxHearts,
                    maxDiamonds
                )
            )
        }

    }

    /**
     * Die Methode veranschaulicht die drei Karten auf Hand des Spielers unter einem String
     *
     * @return einen String mit Information von drei Karten
     */
    override fun toString(): String {
        return this.name
    }
}