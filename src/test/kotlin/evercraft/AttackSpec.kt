package evercraft

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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

        on("when performed by a strong attacker") {
            val attacker = character {
                name = "attacker"
                abilities {
                    strength = 12
                }
            }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 9)
            it("should add strength to attack roll") {
                assertTrue(attack.isHit())
            }

            it("should add strength to damage") {
                assertEquals(2, attack.damage())
            }
        }

        on("when performed by a strong attacker with a critical hit") {
            val attacker = character {
                name = "attacker"
                abilities {
                    strength = 12
                }
            }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 20)
            it("should double the damage dealt") {
                assertEquals(4, attack.damage())
            }
        }

        on("when performed by a very weak attacker") {
            val attacker = character {
                name = "attacker"
                abilities {
                    strength = 1
                }
            }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 10)
            it("should still do 1 damage") {
                assertEquals(1, attack.damage())
            }

        }

        on("with a dextrous defender") {
            val attacker = character { name = "attacker" }
            val defender = character {
                name = "defender"
                abilities {
                    dexterity = 12
                }
            }
            val attack = Attack(attacker, defender, 10)
            it("should add dexterity modifier to armor Class") {
                assertFalse(attack.isHit())
            }
        }


    }

})