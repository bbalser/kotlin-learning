package evercraft.equippable

import evercraft.Character
import evercraft.ThreadLocalDelegate
import kotlin.reflect.KProperty


open class Equipable(block: EquipableBody.() -> Unit) {
    private val body = EquipableBody()

    companion object {
        internal val attackerThreadLocal: ThreadLocal<Character> = ThreadLocal()
        internal val defenderThreadLocal: ThreadLocal<Character> = ThreadLocal()
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

}

class EquipableBody {

    val attacker: Character by ThreadLocalDelegate(Equipable.attackerThreadLocal)
    val defender: Character by ThreadLocalDelegate(Equipable.defenderThreadLocal)

    val strength: MutableList<() -> Int> = mutableListOf()
    val dexterity: MutableList<() -> Int> = mutableListOf()
    val constitution: MutableList<() -> Int> = mutableListOf()
    val wisdom: MutableList<() -> Int> = mutableListOf()
    val intelligence: MutableList<() -> Int> = mutableListOf()
    val charisma: MutableList<() -> Int> = mutableListOf()

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
