package evercraft

import java.lang.Math.max

class Attack(private val attacker: Character, private val defender: Character, private val roll: Int) {

    fun isHit() = (roll + attacker.strength.modifier) >= (defender.armorClass + defender.dexterity.modifier)

    fun defender(): Character = defender.applyAttack(this)

    fun damage(): Int = max(damageMultiplier() * baseDamage(), 1)

    private fun damageMultiplier(): Int = if (roll == 20) 2 else 1

    private fun baseDamage(): Int = 1 + attacker.strength.modifier

}