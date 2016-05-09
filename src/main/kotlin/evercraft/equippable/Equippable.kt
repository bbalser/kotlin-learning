package evercraft.equippable

import evercraft.AbilitiesBuilder
import evercraft.Character
import java.util.concurrent.atomic.AtomicInteger
import kotlin.properties.Delegates

open class Equipable(block: EquipableBody.() -> Unit) {
    private var data: MutableMap<String, Int> = mutableMapOf()

    val strength: Int by data
    val dexterity: Int by data

    init {
        val body = EquipableBody()
        body.block()
        body.build(data)
    }

    fun attackBonus(attacker: Character, defender: Character):Int {
        TODO()
    }

}

class EquipableBody {

    val abilitiesBonusBuilder = AbilitiesBonusBuilder()

    fun abilities(block: AbilitiesBonusBuilder.() -> Unit): Unit = abilitiesBonusBuilder.block()

    class AbilitiesBonusBuilder {

        val strength: IntHolder = IntHolder(0)
        val dexterity: IntHolder = IntHolder(0)

    }

    fun whenAttacking(block: WhenAttackingBody.() -> Unit): Unit = {
        
    }

    internal fun build(data: MutableMap<String, Int>): Unit {
        data["strength"] = abilitiesBonusBuilder.strength.value
        data["dexterity"] = abilitiesBonusBuilder.dexterity.value
    }
}

class WhenAttackingBody {

    val attack: MutableList<(evercraft.Character, evercraft.Character) -> Unit> = mutableListOf()

    operator fun <T> MutableList<T>.plus(other :T): Unit {
        add(other)
    }


}


class IntHolder(initialValue: Int) {
    var value: Int = initialValue

    operator fun plus(other: Int): Unit {
        value = value + other
    }

}


