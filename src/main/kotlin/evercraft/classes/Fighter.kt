package evercraft.classes

import evercraft.CharacterClass

object Fighter : CharacterClass({
    attack + { (level()+1)/2 }
    hitpoints + { level() * 5 }
})
