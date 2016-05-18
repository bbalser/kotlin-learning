package evercraft.classes

import evercraft.Attack
import evercraft.character
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RogueSpec : Spek({

    given("A Rogue") {

        val rogue = character {
            name = "rogue"
            characterClass = Rogue
        }

        on("when critically damaging a defender") {
            val normalAttacker = character {
                name = "normalAttacker"
            }

            val defender = character {
                name = "defender"
            }

            val normalAttack = Attack(normalAttacker, defender, 10)
            val criticalRogueAttack = Attack(rogue, defender, 20)

            it("should do 3 times damage") {
                assertEquals(normalAttack.damage() * 3, criticalRogueAttack.damage())
            }
        }

        on("when attacking a dextrous defender") {

            val dextrousDefender = character {
                name = "defender"
                abilities {
                    dexterity = 12
                }
            }

            val attack = Attack(rogue, dextrousDefender, 10)
            it("should ignore the defenders dexterity bonus") {
                assertTrue(attack.isHit())
            }
        }

        on("when attacking") {

            val dextrousRogue = character {
                name = "sneaky pete"
                abilities {
                    strength = 6
                    dexterity = 12
                }
                characterClass = Rogue
            }

            val defender = character {
                name = "defender"
            }

            val attack = Attack(dextrousRogue, defender, 10)

            it("adds dexterity modifier to attack instead of strength") {
                assertEquals(11, attack.attackRoll)
            }

        }

    }

})