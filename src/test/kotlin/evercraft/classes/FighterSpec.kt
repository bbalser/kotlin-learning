package evercraft.classes

import evercraft.Attack
import evercraft.character
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class FighterSpec : Spek({

    given("A fighter") {

        for (level in (1..20)) {
            on("with a level of $level") {
                val fighter = character {
                    name = "fighter"
                    experiencePoints = (level - 1) * 1000
                    characterClass = Fighter
                }

                val defender = character {
                    name = "defender"
                }

                val attack = Attack(fighter, defender, 0)

                it("should have an attack roll of $level") {
                    assertEquals(level, attack.attackRoll)
                }

                it("should have ${level*10} hitpoints") {
                    assertEquals(level*10, fighter.hitPoints)
                }

            }

        }
    }


})
