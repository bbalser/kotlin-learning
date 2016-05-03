package evercraft

fun abilities(block: AbilitiesBuilder.() -> Unit): Abilities {
    val abilities = AbilitiesBuilder()
    abilities.block()
    return abilities.build()
}

class Abilities internal constructor (val strength: Ability)

class AbilitiesBuilder {

    var strength: Int = 10

    fun build() : Abilities = Abilities(strength = Ability(strength))

}