package evercraft.equippable

import evercraft.AbilitiesBuilder
import kotlin.properties.Delegates

open class Equipable(block: EquipableBody.() -> Unit) {
    val body = EquipableBody()

    init {
        body.block()
    }

}

class EquipableBody {

    val abilitiesBuilder = AbilitiesBuilder()

    fun abilities(block: AbilitiesBuilder.() -> Unit): Unit = abilitiesBuilder.block()


}