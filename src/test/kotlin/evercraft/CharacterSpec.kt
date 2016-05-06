package evercraft

import com.sun.javaws.exceptions.InvalidArgumentException
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
                armorClass = 12
                alignment = Alignment.EVIL
                abilities {
                    wisdom = 17
                    dexterity = 14
                }
            }

            it("should have 4 hitPoints") {
                assertEquals(4, character.hitPoints)
            }

            it("should have an armorClass of 12") {
                assertEquals(12, character.armorClass)
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

    }


})