package evercraft.classes

import evercraft.character
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class MonkSpec: Spek({

    given("A Monk") {

        for (level in 1..20) {
            on("when created at level $level") {

                val monk = character {
                    name = "monk"
                    experiencePoints = (level-1) * 1000
                    characterClass = Monk
                }

                it("should have ${level * 6} hit points") {
                    assertEquals(level * 6, monk.hitPoints)
                }

            }

        }

        on("when attacking") {

            val monk = character {
                name = "monk"
                characterClass = Monk
            }

            it("should do 3 damage") {
                assertEquals(3, monk.baseDamage())
            }

        }

    }

})