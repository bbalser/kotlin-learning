package evercraft.classes

import evercraft.CharacterClass

object Monk: CharacterClass({

    hitpoints + { my.level }

    damage + 2

})