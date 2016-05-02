package evercraft

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class CharacterSpec : Spek({

    given("A Character") {

        val character = Character(name = "Brian")
        on("when created") {
            it("should return name") {
                assertEquals("Brian", character.name)
            }

            it("should have default alignment") {
                assertEquals(Alignment.NEUTRAL, character.alignment )
            }

            it("should have default armor class") {
                assertEquals(10, character.armorClass)
            }

            it("should have default hit points") {
                assertEquals(5, character.hitPoints)
            }
        }

        on("when renamed") {
            val newCharacter = character.rename("Joe")
            it("should have the new name") {
                assertEquals("Joe", newCharacter.name)
            }
        }



    }

})