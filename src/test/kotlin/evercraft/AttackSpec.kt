package evercraft

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class AttackSpec: Spek({

    given("An attack") {

        on("when executed") {

            data class AttackScenario(val roll: Int, val armorClass: Int, val hit: Boolean)

            val attackData = listOf(
                    AttackScenario(10, 10, true),
                    AttackScenario(11, 10, true),
                    AttackScenario(9, 10, false)
            )

            for ((roll, armorClass, isHit) in attackData) {
                val attacker = character { name = "attacker" }
                val defender = character {
                    name = "defender"
                    this.armorClass = armorClass
                }
                val attack = Attack(attacker, defender, roll)
                it("will ${if (isHit) "hit" else "not hit"} when attack is $roll and defender's armorClass is $armorClass") {
                    assertEquals(isHit, attack.isHit())
                }
            }

        }

        on("when successful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 10)
            it("should inflict 1 point of damage on defender") {
                assertEquals(4, attack.defender().hitPoints)
            }
        }

        on("when critically successful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 20)
            it("should inflict 2 points of damage on defender") {
                assertEquals(3, attack.defender().hitPoints)
            }
        }

        on("when unsuccessful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 9)
            it("shoult not inflict any damage to defender") {
                assertEquals(5, attack.defender().hitPoints)
            }
        }

    }

})