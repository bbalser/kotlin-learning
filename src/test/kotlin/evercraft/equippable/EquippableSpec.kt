package evercraft.equippable

import org.jetbrains.spek.api.Spek

class EquippableSpec: Spek({

    val equipable = object: Equipable({

        abilities {
            strength + 1
        }    

    })


})
