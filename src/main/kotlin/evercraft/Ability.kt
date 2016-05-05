package evercraft

import com.sun.javaws.exceptions.InvalidArgumentException
import kotlin.reflect.KProperty

class Ability(val score: Int) {

    val modifier: Int by lazy {
        Math.floor((score - 10.0) / 2.0).toInt()
    }

    operator override fun equals(other: Any?): Boolean = when (other) {
            is Ability -> score == other.score
            is Int -> score == other
            else -> false
        }
}

class AbilitiesBuilder {

    var strength: Int by AbilityValidationDelegate()
    var dexterity: Int by AbilityValidationDelegate()
    var constitution: Int by AbilityValidationDelegate()
    var wisdom: Int by AbilityValidationDelegate()
    var intelligence: Int by AbilityValidationDelegate()
    var charisma: Int by AbilityValidationDelegate()

    fun build() : Map<String, Ability> = mapOf(
            "strength" to Ability(strength),
            "dexterity" to Ability(dexterity),
            "constitution" to Ability(constitution),
            "wisdom" to Ability(wisdom),
            "intelligence" to Ability(intelligence),
            "charisma" to Ability(charisma))
}

private class AbilityValidationDelegate : IntValidationDelegate(10, 1, 20)

private open class IntValidationDelegate(val initialValue: Int, val min: Int, val max: Int) {

    private var value: Int = initialValue

    operator fun getValue(thisRef: AbilitiesBuilder, property: KProperty<*>): Int = value

    operator fun setValue(thisRef: AbilitiesBuilder, property: KProperty<*>, value: Int) = when {
        value > max -> throw IllegalArgumentException("Integer value(${property.name}) cannot be greater than $max")
        value < min -> throw IllegalArgumentException("Integer value(${property.name}) cannot be less than $min")
        else -> this.value = value
    }
}