package evercraft.equippable

import evercraft.character
import evercraft.with
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class EquippableSpec : Spek({

    given("A test equipable") {

        on("when created with specified values") {
            val equipable = object : Equipable({

                strength + 2
                dexterity + 1
                constitution + 2
                wisdom + 5
                intelligence + 1
                charisma + 8

            }) {}

            it("should have a strength of 3") {
                assertEquals(2, equipable.strength)
            }

            it("should have a dexterity of 1") {
                assertEquals(1, equipable.dexterity)
            }

            it("should have a constitution of 2") {
                assertEquals(2, equipable.constitution)
            }

            it("should have a wisdom of 5") {
                assertEquals(5, equipable.wisdom)
            }

            it("should have a intelligence of 1") {
                assertEquals(1, equipable.intelligence)
            }

            it("should have a charisma of 8") {
                assertEquals(8, equipable.charisma)
            }

        }


    }

})

