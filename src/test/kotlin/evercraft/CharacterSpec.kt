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
        }

        on("when renamed") {
            val newCharacter = character.rename("Joe")
            it("should have the new name") {
                assertEquals("Joe", newCharacter.name)
            }
        }

        

    }

})