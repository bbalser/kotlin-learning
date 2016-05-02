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
                val attacker = Character(name = "attacker")
                val defender = Character(name = "defender", armorClass = armorClass)
                val attack = Attack(attacker, defender, roll)
                it("will ${if (isHit) "hit" else "not hit"} when attack is $roll and defender's armorClass is $armorClass") {
                    assertEquals(isHit, attack.isHit())
                }
            }

        }
    }

})