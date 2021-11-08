package entity

data class Player(val name: String, var points: Int, var hasKnocked: Boolean) {
    var hand = emptyArray<Card>()
    override fun toString(): String {
        return "$hand[1] $hand[2] $hand[3]"
    }
}