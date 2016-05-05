package evercraft

data class Character internal constructor (val name: String,
                     val alignment: Alignment,
                     val armorClass: Int,
                     val hitPoints: Int,
                     val abilities: Map<String, Ability>) {

    val strength: Ability by abilities
    val dexterity: Ability by abilities
    val constitution: Ability by abilities
    val wisdom: Ability by abilities
    val intelligence: Ability by abilities
    val charisma: Ability by abilities

    fun rename(newName: String): Character = copy(name = newName)

    fun applyAttack(attack: Attack): Character = if (attack.isHit()) copy(hitPoints = hitPoints - attack.damage()) else this

    fun isAlive(): Boolean = if (hitPoints > 0) true else false
}

fun character(block: CharacterBuilder.() -> Unit): Character {
    val builder = CharacterBuilder()
    builder.block()
    return builder.build()
}

class CharacterBuilder {

    private val abilitesBuilder: AbilitiesBuilder = AbilitiesBuilder()

    var name: String = ""
    var hitPoints: Int = 5
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