package evercraft

class Ability(val score: Int) {

    operator override fun equals(other: Any?): Boolean = when (other) {
            is Ability -> score == other.score
            is Int -> score == other
            else -> false
        }    

}