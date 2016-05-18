package evercraft

import evercraft.equippable.Equipable
import evercraft.equippable.EquipableBody
import java.lang.Math.*
import kotlin.properties.Delegates

class Character internal constructor(val name: String,
                                     val alignment: Alignment,
                                     val armorClass: Int,
                                     val abilities: Map<String, Ability>,
                                     hitPoints: Int?,
                                     val experiencePoints: Int = 0,
                                     val characterClass: CharacterClass) {

    val strength: Ability by abilities
    val dexterity: Ability by abilities
    val constitution: Ability by abilities
    val wisdom: Ability by abilities
    val intelligence: Ability by abilities
    val charisma: Ability by abilities
    val level: Int by lazy {
        min((floor(experiencePoints / 1000.0) + 1).toInt(), 20)
    }
    val hitPoints: Int = hitPoints ?: determineDefaultHitPoints()

    fun rename(newName: String): Character = copy(name = newName)

    fun applyAttack(attack: Attack): Character = if (attack.isHit()) copy(hitPoints = hitPoints - attack.damage()) else this

    fun addExperience(attack: Attack): Character = if (attack.isHit()) copy(experiencePoints = experiencePoints + 10) else this

    fun isAlive(): Boolean = if (hitPoints > 0) true else false

    fun attackModifier(defender: Character): Int = Equipable.withCharacter(this) {
        Equipable.withDefender(defender) {
            level / 2 + strength.modifier + characterClass.attack
        }
    }

    fun criticalDamageMultiplier(): Int = max(2, characterClass.criticalDamageMultiplier)

    fun baseDamage(): Int = Equipable.withCharacter(this) {
        1 + strength.modifier + characterClass.damage
    }

    private fun copy(name: String = this.name,
                     alignment: Alignment = this.alignment,
                     armorClass: Int = this.armorClass,
                     abilities: Map<String, Ability> = this.abilities,
                     hitPoints: Int = this.hitPoints,
                     experiencePoints: Int = this.experiencePoints,
                     characterClass: CharacterClass = this.characterClass) =
            Character(name, alignment, armorClass, abilities, hitPoints, experiencePoints, characterClass)

    private fun determineDefaultHitPoints(): Int = Equipable.withCharacter(this) {
        max(level * (5 + constitution.modifier) + characterClass.hitPoints, 1)
    }
}


fun character(block: CharacterBuilder.() -> Unit): Character {
    val builder = CharacterBuilder()
    builder.block()
    return builder.build()
}

class CharacterBuilder {

    private val abilitesBuilder: AbilitiesBuilder = AbilitiesBuilder()

    var name: String by Delegates.notNull()
    var hitPoints: Int? = null
    var armorClass: Int = 10
    var alignment: Alignment = Alignment.NEUTRAL
    var experiencePoints: Int = 0
    var characterClass: CharacterClass? = null

    fun abilities(block: AbilitiesBuilder.() -> Unit): Unit {
        abilitesBuilder.block()
    }

    fun build(): Character = Character(name = name,
            hitPoints = hitPoints,
            armorClass = armorClass,
            alignment = alignment,
            abilities = abilitesBuilder.build(),
            experiencePoints = experiencePoints,
            characterClass = characterClass ?: CharacterClass { })

}

open class CharacterClass(block: EquipableBody.() -> Unit) : Equipable(block)