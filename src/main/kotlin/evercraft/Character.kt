package evercraft

import evercraft.equippable.Equipable
import evercraft.equippable.EquipableBody
import evercraft.equippable.withCharacter
import evercraft.equippable.withDefender
import evercraft.races.Human
import java.lang.Math.*
import kotlin.properties.Delegates

class Character internal constructor(val name: String,
                                     val alignment: Alignment,
                                     val abilities: Map<String, Ability>,
                                     hitPoints: Int?,
                                     val experiencePoints: Int = 0,
                                     val characterClass: CharacterClass,
                                     val race: Race) {

    val strength: Ability by abilities
    val dexterity: Ability by abilities
    val constitution: Ability by abilities
    val wisdom: Ability by abilities
    val intelligence: Ability by abilities
    val charisma: Ability by abilities
    val level: Int by lazy {
        min((floor(experiencePoints / 1000.0) + 1).toInt(), 20)
    }

    val armorClass: Int by lazy {
        withCharacter(this) {
            10 + dexterity.modifier + characterClass.armorClass
        }
    }

    val hitPoints: Int = hitPoints ?: determineDefaultHitPoints()

    fun rename(newName: String): Character = copy(name = newName)

    fun applyAttack(attack: Attack): Character = if (attack.isHit()) copy(hitPoints = hitPoints - attack.damage()) else this

    fun addExperience(attack: Attack): Character = if (attack.isHit()) copy(experiencePoints = experiencePoints + 10) else this

    fun isAlive(): Boolean = if (hitPoints > 0) true else false

    fun attackModifier(defender: Character): Int = withCharacter(this) {
        withDefender(defender) {
            level / 2 + strength.modifier + characterClass.attack
        }
    }

    fun criticalDamageMultiplier(): Int = max(2, characterClass.criticalDamageMultiplier)

    fun baseDamage(): Int = withCharacter(this) {
        1 + strength.modifier + characterClass.damage
    }

    private fun copy(name: String = this.name,
                     alignment: Alignment = this.alignment,
                     abilities: Map<String, Ability> = this.abilities,
                     hitPoints: Int = this.hitPoints,
                     experiencePoints: Int = this.experiencePoints,
                     characterClass: CharacterClass = this.characterClass,
                     race: Race = this.race) =
            Character(name, alignment, abilities, hitPoints, experiencePoints, characterClass, race)

    private fun determineDefaultHitPoints(): Int = withCharacter(this) {
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
    var alignment: Alignment = Alignment.NEUTRAL
    var experiencePoints: Int = 0
    var characterClass: CharacterClass? = null
    var race : Race = Human

    fun abilities(block: AbilitiesBuilder.() -> Unit): Unit {
        abilitesBuilder.block()
    }

    fun build(): Character = Character(name = name,
            hitPoints = hitPoints,
            alignment = alignment,
            abilities = abilitesBuilder.build(),
            experiencePoints = experiencePoints,
            characterClass = characterClass ?: CharacterClass { },
            race = race)

}

open class CharacterClass(block: EquipableBody.() -> Unit) : Equipable(block)

open class Race(block: EquipableBody.() -> Unit) : Equipable(block)