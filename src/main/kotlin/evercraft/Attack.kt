package evercraft

class Attack(val attacker: Character, val defender: Character, val roll: Int) {

    fun isHit(): Boolean = roll >= defender.armorClass

}