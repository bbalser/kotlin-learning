package evercraft.equippable

import evercraft.Alignment
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class EquippableSpec : Spek({

    given("A test equipable") {

        on("when created with specified values") {
        val equipable = object : Equipable({

            abilities {
                strength + 1
                dexterity + 2
            }

            whenAttacking {
                attack + { attacker, defender -> if (defender.alignment == Alignment.EVIL) 2 else 0 }
            }

        }) {}

            it("should have a strength of 1") {
                assertEquals(1, equipable.strength)
            }

            it("should have a dexterity of 1") {
                assertEquals(2, equipable.dexterity)
            }
        }


    }


})
