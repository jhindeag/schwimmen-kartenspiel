package entity

class Player(val name: String, var points: Int, var hasKnocked: Boolean) {
    var hand = arrayOfNulls<Card>(3)
}