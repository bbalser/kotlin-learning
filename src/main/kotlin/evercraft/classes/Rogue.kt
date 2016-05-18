package evercraft.classes

import evercraft.CharacterClass

object Rogue : CharacterClass({

    criticalDamageMultiplier = 3

    attack + { defender.dexterity.modifier }
    attack + { my.dexterity.modifier - my.strength.modifier }

})