package evercraft.classes

import evercraft.CharacterClass

object Fighter : CharacterClass({

    attack + { (my.level + 1) / 2 }

    hitpoints + { my.level * 5 }

})
