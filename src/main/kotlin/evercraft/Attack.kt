package evercraft

class Attack(private val attacker: Character, private val defender: Character, private val roll: Int) {

    fun isHit() = roll >= defender.armorClass

    fun defender(): Character = defender.applyAttack(this)

    fun damage(): Int = if (roll == 20) 2 else 1

}