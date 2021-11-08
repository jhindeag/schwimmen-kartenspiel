package entity

data class Card(val cardSuit: CardSuit, val cardValue: CardValue) {
    override fun toString(): String {
        return this.cardValue.toString() + this.cardSuit.toString()
    }
}