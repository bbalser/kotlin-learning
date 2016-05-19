package evercraft.classes

import evercraft.CharacterClass
import kotlin.ranges.IntProgression.Companion.fromClosedRange

object Monk: CharacterClass({

    hitpoints + { my.level }

    damage + 2

    armorClass + { my.wisdom.modifier }

    attack + {
        fromClosedRange(2, my.level, 3).count() +
        fromClosedRange(3, my.level, 3).count()
    }

})