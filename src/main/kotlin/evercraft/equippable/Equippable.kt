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
        body.block()
    }

    val strength: Int by LamdaListToIntDelegate<Equipable>(body.strength)
    val dexterity: Int by LamdaListToIntDelegate<Equipable>(body.dexterity)
    val constitution: Int by LamdaListToIntDelegate<Equipable>(body.constitution)
    val wisdom: Int by LamdaListToIntDelegate<Equipable>(body.wisdom)
    val intelligence: Int by LamdaListToIntDelegate<Equipable>(body.intelligence)
    val charisma: Int by LamdaListToIntDelegate<Equipable>(body.charisma)
    val hitPoints: Int by LamdaListToIntDelegate<Equipable>(body.hitpoints)
    val attack: Int by LamdaListToIntDelegate<Equipable>(body.attack)
    val damage: Int by LamdaListToIntDelegate<Equipable>(body.damage)
    val armorClass: Int by LamdaListToIntDelegate<Equipable>(body.armorClass)
    val criticalDamageMultiplier: Int by lazy {
        body.criticalDamageMultiplier
    }

}

class EquipableBody {

    val strength: MutableList<() -> Int> = mutableListOf()
    val dexterity: MutableList<() -> Int> = mutableListOf()
    val constitution: MutableList<() -> Int> = mutableListOf()
    val wisdom: MutableList<() -> Int> = mutableListOf()
    val intelligence: MutableList<() -> Int> = mutableListOf()
    val charisma: MutableList<() -> Int> = mutableListOf()
    val hitpoints: MutableList<() -> Int> = mutableListOf()
    val attack: MutableList<() -> Int> = mutableListOf()
    val damage: MutableList<() -> Int> = mutableListOf()
    val armorClass: MutableList<() -> Int> = mutableListOf()
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

class LamdaListToIntDelegate<K>(list: List<() -> Int> = listOf()) {

    val list: List<() -> Int> = list

    operator fun getValue(thisRef: K, property: KProperty<*>): Int = list.map { it() }.sum()

}
