package evercraft

import org.jetbrains.spek.api.Spek

class AbilitySpec: Spek({

    given("Abilities") {

        on("when created with strength of 11") {

            val abilities = abilities {
                strength = 10
            }

            it("should have a strength of 11") {
                abilities.strength == Ability(10)
            }

        }

    }

})