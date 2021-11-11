package entity

data class Player(val name: String, val hand: ArrayList<Card> = ArrayList<Card>(3)) {
    var points = 0
    var hasKnocked = false

    /**
     * Die Methode überprüft, ob der Spieler einen gültigen Namen eingegeben hat
     * Ein ungültiger Name wäre ein leerer String
     */
    init {
        if (name == "") {
            error("Name could not be empty")
        }
    }

    /**
     * Die Methode veranschaulicht die drei Karten auf Hand des Spielers unter einem String
     *
     * @return einen String mit Information von drei Karten
     */
    override fun toString(): String {
        return hand[0].toString() + " " + hand[1].toString() + " " + hand[2].toString()
    }
}