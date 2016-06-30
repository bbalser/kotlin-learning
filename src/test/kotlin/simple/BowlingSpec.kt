package simple

import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class BowlingSpec : Spek({

    describe("A Bowling game") {

        for (pins in 1..9) {
            it("returns $pins when $pins are knock down on a single roll") {
                assertEquals(pins, bowling(pins.toString()))
            }
        }

        it("returns 0 when a gutter ball is rolled") {
            assertEquals(0, bowling("-"))
        }

        it("returns 10 when roll is a strike") {
            assertEquals(10, bowling("X"))
        }

        it("returns 2 when with rolls of 1 and 1") {
            assertEquals(2, bowling("11"))
        }

        it("returns 30 with rolls of X55") {
            assertEquals(30, bowling("X55"))
        }

        it("returns 24 with rolls of 5/7") {
            assertEquals(24, bowling("5/7"))
        }

        it("returns 36 when rolls of XX2") {
            assertEquals(36, bowling("XX2"))
        }

        it("returns 30 when rolls of X4/") {
            assertEquals(30, bowling("X4/"))
        }

        it ("returns 300 when rolls all strikes") {
            assertEquals(300, bowling("XXXXXXXXXXXX"))
        }

    }

})