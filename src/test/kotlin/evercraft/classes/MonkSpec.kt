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

        val attackDamageData = listOf(
                1 to 0,
                2 to 2,
                3 to 3,
                4 to 4,
                5 to 5,
                6 to 7,
                7 to 7,
                8 to 9
        )

        for ((level, attackModifier) in attackDamageData) {
            on("when attacking at level $level") {

                val leveledMonk = character {
                    name = "monk"
                    characterClass = Monk
                    experiencePoints = (level-1) * 1000
                }

                it("should have attackModifier of $attackModifier") {
                    assertEquals(attackModifier, leveledMonk.attackModifier(character { name = "defender" }))
                }

            }
        }


        on("when created") {
            val monk = character {
                name = "monk"
                characterClass = Monk
                abilities {
                    wisdom = 12
                    dexterity = 12
                }
            }

            it("should add wisdom modifier and dexterity modifer to armorClass") {
                assertEquals(12, monk.armorClass)
            }

        }

    }

})