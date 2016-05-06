package evercraft

import kotlin.properties.Delegates

class Character internal constructor(val name: String,
                                     val alignment: Alignment,
                                     val armorClass: Int,
                                     val abilities: Map<String, Ability>,
                                     hitPoints: Int?) {

    val strength: Ability by abilities
    val dexterity: Ability by abilities
    val constitution: Ability by abilities
    val wisdom: Ability by abilities
    val intelligence: Ability by abilities
    val charisma: Ability by abilities
    val hitPoints: Int = hitPoints ?: determineDefaultHitPoints()

    fun rename(newName: String): Character = copy(name = newName)

    fun applyAttack(attack: Attack): Character = if (attack.isHit()) copy(hitPoints = hitPoints - attack.damage()) else this

    fun isAlive(): Boolean = if (hitPoints > 0) true else false

    private fun copy(name: String = this.name,
                     alignment: Alignment = this.alignment,
                     armorClass: Int = this.armorClass,
                     abilities: Map<String, Ability> = this.abilities,
                     hitPoints: Int = this.hitPoints) = Character(name, alignment, armorClass, abilities, hitPoints)

    private fun determineDefaultHitPoints(): Int = 5 + constitution.modifier
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

    fun abilities(block: AbilitiesBuilder.() -> Unit): Unit {
        abilitesBuilder.block()
    }

    fun build(): Character = Character(name = name,
            hitPoints = hitPoints,
            armorClass = armorClass,
            alignment = alignment,
            abilities = abilitesBuilder.build())

}