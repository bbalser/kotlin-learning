package evercraft

import java.lang.Math.max

class Attack(attacker: Character, defender: Character, val roll: Int) {

    private val originalDefender: Character = defender
    val defender: Character by lazy {
        originalDefender.applyAttack(this)
    }

    private val originalAttacker: Character = attacker
    val attacker: Character by lazy {
        originalAttacker.addExperience(this)
    }

    val attackRoll: Int by lazy {
        roll + originalAttacker.attackModifier(originalDefender)
    }

    fun isHit() = attackRoll >= (originalDefender.armorClass + originalDefender.dexterity.modifier)

    fun damage(): Int = max(damageMultiplier() * originalAttacker.baseDamage(), 1)

    private fun damageMultiplier(): Int = if (roll == 20) originalAttacker.criticalDamageMultiplier() else 1
}