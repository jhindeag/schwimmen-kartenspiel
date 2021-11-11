package entity

data class Card(val cardSuit: CardSuit, val cardValue: CardValue) {
    /**
     * Die Methode veranschaulicht eine Karte unter einem String
     *
     * @return einen String mit dem Wert und der Farbe der Karte
     */
    override fun toString(): String {
        return this.cardValue.toString() + this.cardSuit.toString()
    }
}