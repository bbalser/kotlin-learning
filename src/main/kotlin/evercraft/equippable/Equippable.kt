package evercraft.equippable

import evercraft.Character
import evercraft.ThreadLocalDelegate
import evercraft.with
import kotlin.reflect.KProperty


open class Equipable(block: EquipableBody.() -> Unit) {
    private val body = EquipableBody()

    companion object {
        internal val characterThreadLocal: ThreadLocal<Character> = ThreadLocal()
        internal val defenderThreadLocal: ThreadLocal<Character> = ThreadLocal()

        fun <R> withCharacter(character: Character, block: () -> R): R {
            return characterThreadLocal.with(character, block)
        }

        fun <R> withDefender(defender: Character, block: () -> R): R {
            return defenderThreadLocal.with(defender, block)
        }
    }

    init {
//        body.block()
    }

    val strength: Int by DynamicEquippableBodyBlock(block)
    val dexterity: Int by LamdaListToIntDelegate<Equipable>(body.dexterity)
    val constitution: Int by LamdaListToIntDelegate<Equipable>(body.constitution)
    val wisdom: Int by LamdaListToIntDelegate<Equipable>(body.wisdom)
    val intelligence: Int by LamdaListToIntDelegate<Equipable>(body.intelligence)
    val charisma: Int by LamdaListToIntDelegate<Equipable>(body.charisma)
    val hitPoints: Int by LamdaListToIntDelegate<Equipable>(body.hitpoints)
    val attack: Int by LamdaListToIntDelegate<Equipable>(body.attack)
    val damage: Int by LamdaListToIntDelegate<Equipable>(body.damage)
    val criticalDamageMultiplier: Int by lazy {
        body.criticalDamageMultiplier
    }

}

class EquipableBody {

    internal val values: MutableMap<String, Int> = mutableMapOf(
            "strength" to 0
    )

    var strength: Int by values
    val dexterity: MutableList<() -> Int> = mutableListOf()
    val constitution: MutableList<() -> Int> = mutableListOf()
    val wisdom: MutableList<() -> Int> = mutableListOf()
    val intelligence: MutableList<() -> Int> = mutableListOf()
    val charisma: MutableList<() -> Int> = mutableListOf()
    val hitpoints: MutableList<() -> Int> = mutableListOf()
    val attack: MutableList<() -> Int> = mutableListOf()
    val damage: MutableList<() -> Int> = mutableListOf()
    var criticalDamageMultiplier: Int = 0

    val my: Character by ThreadLocalDelegate(Equipable.characterThreadLocal)
    val defender: Character by ThreadLocalDelegate(Equipable.defenderThreadLocal)

    operator fun MutableList<() -> Int>.plus(other: Int): Unit {
        this.add({ -> other })
    }

    operator fun MutableList<() -> Int>.plus(other: () -> Int): Unit {
        this.add(other)
    }

}

class DynamicEquippableBodyBlock(val block: EquipableBody.() -> Unit) {

    operator fun getValue(thisRef: Equipable, property: KProperty<*>): Int {
        val body = EquipableBody()
        body.block()
        return body.values[property.name] ?: 0
    }

}


class LamdaListToIntDelegate<K>(list: List<() -> Int> = listOf()) {

    val list: List<() -> Int> = list

    operator fun getValue(thisRef: K, property: KProperty<*>): Int = list.map { it() }.sum()

}
