package evercraft

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AttackSpec: Spek({

    given("An attack") {

        on("when executed") {

            data class AttackScenario(val roll: Int, val hit: Boolean)

            val attackData = listOf(
                    AttackScenario(10, true),
                    AttackScenario(11, true),
                    AttackScenario(9, false)
            )

            for ((roll, isHit) in attackData) {
                val attacker = character { name = "attacker" }
                val defender = character {
                    name = "defender"
                }
                val attack = Attack(attacker, defender, roll)
                it("will ${if (isHit) "hit" else "not hit"} when attack is $roll and defender's armorClass is ${defender.armorClass}") {
                    assertEquals(isHit, attack.isHit())
                }
            }

        }

        on("when successful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 10)
            it("should inflict 1 point of damage on defender") {
                assertEquals(4, attack.defender.hitPoints)
            }

            it("should give the attacker 10 experience points") {
                assertEquals(10, attack.attacker.experiencePoints)
            }
        }

        on("when critically successful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 20)
            it("should inflict 2 points of damage on defender") {
                assertEquals(3, attack.defender.hitPoints)
            }
        }

        on("when unsuccessful") {
            val attacker = character { name = "attacker" }
            val defender = character { name = "defender" }
            val attack = Attack(attacker, defender, 9)
            it("shoult not inflict any damage to defender") {
                assertEquals(5, attack.defender.hitPoints)
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


        val attackRollLevels = listOf(
                1 to 10,
                2 to 9,
                3 to 9,
                4 to 8,
                5 to 8,
                6 to 7,
                7 to 7,
                8 to 6,
                9 to 6,
                10 to 5,
                11 to 5,
                12 to 4,
                13 to 4,
                14 to 3,
                15 to 3,
                16 to 2,
                17 to 2,
                18 to 1,
                19 to 1
        )

        for ((level, roll) in attackRollLevels) {
            val attacker = character {
                name = "attacker"
                experiencePoints = (level-1) * 1000
            }
            val defender = character { name = "defender" }
            on("when attacker is level ${level} and rolls a ${roll - 1}") {
                val attack = Attack(attacker, defender, roll - 1)
                it ("should miss") {
                    assertFalse(attack.isHit())
                }
            }

            on("when attacker is level ${level} and rolls a ${roll}") {
                val attack = Attack(attacker, defender, roll)
                it("should hit") {
                    assertTrue(attack.isHit())
                }
            }
        }
    }

})