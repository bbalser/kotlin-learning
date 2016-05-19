package evercraft.equippable

import evercraft.character
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

                attack + 5

                hitpoints + { my.level }

                criticalDamageMultiplier = 3

            }) {}

            val person = character {
                name = "generic"
                experiencePoints = 17980
            }


            it("should have a strength of 3") {
                Equipable.withCharacter(person) {
                    assertEquals(2, equipable.strength)
                }
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

            it("should have an intelligence of 1") {
                assertEquals(1, equipable.intelligence)
            }

            it("should have a charisma of 8") {
                assertEquals(8, equipable.charisma)
            }

            it("should have 11 hitpoints") {
                Equipable.withCharacter(person) {
                    assertEquals(18, equipable.hitPoints)
                }
            }

            it("should have an attack of 5") {
                assertEquals(5, equipable.attack)
            }

            it("should have a critical damage multiplier of 3") {
                assertEquals(3, equipable.criticalDamageMultiplier)
            }

        }


    }

})

