package evercraft

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class AbilitySpec: Spek({

    given("An Ability") {

        val scores = listOf(
                1 to -5,
                2 to -4,
                3 to -4,
                4 to -3,
                5 to -3,
                6 to -2,
                7 to -2,
                8 to -1,
                9 to -1,
                10 to 0,
                11 to 0,
                12 to 1,
                13 to 1,
                14 to 2,
                15 to 2,
                16 to 3,
                17 to 3,
                18 to 4,
                19 to 4,
                20 to 5
        )

        for ((score, mod) in scores) {
            on("created with a score of $score") {
                val ability = Ability(score)
                it("should have a modifier of $mod") {
                    assertEquals(mod, ability.modifier)
                }
            }
        }


    }


})