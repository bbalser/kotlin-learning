package evercraft

data class Character(val name: String,
                     val alignment: Alignment = Alignment.NEUTRAL,
                     val armorClass: Int = 10,
                     val hitPoints: Int = 5) {

    fun rename(newName: String): Character = copy(name = newName)

    fun applyAttack(attack: Attack): Character = if (attack.isHit()) copy(hitPoints = hitPoints - attack.damage()) else this

    fun isAlive(): Boolean = if (hitPoints > 0) true else false
}