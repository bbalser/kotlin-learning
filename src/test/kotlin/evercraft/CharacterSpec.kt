package evercraft

import evercraft.races.Human
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail

class CharacterSpec : Spek({

    given("A default Character") {

        val character = character {
            name = "Brian"
        }

        on("when created") {
            it("should return name") {
                assertEquals("Brian", character.name)
            }

            it("should have default alignment") {
                assertEquals(Alignment.NEUTRAL, character.alignment)
            }

            it("should have default armor class") {
                assertEquals(10, character.armorClass)
            }

            it("should have default hit points") {
                assertEquals(5, character.hitPoints)
            }

            it("should be alive") {
                assertTrue(character.isAlive())
            }

            it("should have a default strength") {
                assertEquals(10, character.strength.score)
            }

            it ("should have a default dexterity") {
                assertEquals(10, character.dexterity.score)
            }

            it ("should have a default constitution") {
                assertEquals(10, character.constitution.score)
            }

            it ("should have a default wisdom") {
                assertEquals(10, character.wisdom.score)
            }

            it ("should have a default intelligence") {
                assertEquals(10, character.intelligence.score)
            }

            it ("should have a default charisma") {
                assertEquals(10, character.charisma.score)
            }

            it("should have a default experience points") {
                assertEquals(0, character.experiencePoints)
            }

            it ("should have a default level of 1") {
                assertEquals(1, character.level)
            }

            it ("should be human") {
                assertTrue(character.race is Human)
            }

        }

        on("when renamed") {
            val newCharacter = character.rename("Joe")
            it("should have the new name") {
                assertEquals("Joe", newCharacter.name)
            }
        }

        on("when damaged until hitpoints are zero") {
            val damagedCharacter = character {
                name = "damaged"
                hitPoints = 0
            }
            it("should be dead") {
                assertFalse(damagedCharacter.isAlive())
            }
        }
    }

    given("A customized character") {

        on("when created") {

            val character = character {
                name = "default"
                hitPoints = 4
                alignment = Alignment.EVIL
                abilities {
                    wisdom = 17
                    dexterity = 14
                }
            }

            it("should have 4 hitPoints") {
                assertEquals(4, character.hitPoints)
            }

            it("should have an alignment of Evil") {
                assertEquals(Alignment.EVIL, character.alignment)
            }

            it ("should have a wisdom attribute of 17") {
                assertEquals(17, character.wisdom.score)
            }

            it ("should have a dexterity of 14") {
                assertEquals(14, character.dexterity.score)
            }

        }

        on("when created with a attibute value of 21") {
            it ("should throw an assertion error") {

                try {
                    character {
                        name = "Brian"
                        abilities {
                            strength = 21
                        }
                    }
                    fail("Should have thrown ${IllegalArgumentException::class.qualifiedName}")
                } catch(e: IllegalArgumentException) {
                    assertEquals("Integer value(strength) cannot be greater than 20", e.message)
                }
            }
        }

        on("when created with an attribute value of zero") {
            it ("should throw an assertion error") {

                try {
                    character {
                        name = "Brian"
                        abilities {
                            strength = 0
                        }
                    }
                    fail("Should have thrown ${IllegalArgumentException::class.qualifiedName}")
                } catch (e: IllegalArgumentException) {
                    assertEquals("Integer value(strength) cannot be less than 1", e.message)
                }
            }
        }

        on("when created with a above average constitution") {
            val character = character {
                name = "hearty"
                abilities {
                    constitution = 12
                }
            }
            it("should include constitution modifier in hitpoints") {
                assertEquals(6, character.hitPoints)
            }
        }

        on("when created as a very unhealthy character") {
            val character = character {
                name = "sickly"
                abilities {
                    constitution = 1
                }
            }
            it("should still have 1 hitpoint") {
                assertEquals(1, character.hitPoints)
            }
        }

        val expData = listOf(
                0..999 to 1,
                1000..1999 to 2,
                2000..2999 to 3,
                3000..3999 to 4,
                4000..4999 to 5,
                5000..5999 to 6,
                6000..6999 to 7,
                19000..19999 to 20,
                20000..29999 to 20
        )

        for ((range, level) in expData) {
            on("with experience points in range ${range.first} to ${range.last}") {
                it("should be level ${level}") {
                    range.forEach { exp ->
                        val character = character {
                            name = "exp"
                            experiencePoints = exp
                        }
                        assertEquals(level, character.level, "Experience: ${exp}")
                    }
                }

                val character = character {
                    name = "jake"
                    experiencePoints = range.first
                }

                it("should have ${5 * character.level} hitpoints") {
                    assertEquals(5 * character.level, character.hitPoints)
                }

            }

        }


    }


})