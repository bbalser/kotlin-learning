import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class RomanSpec : Spek({

    given("A Roman Converter") {

        val data = listOf(
                1 to "I",
                2 to "II",
                3 to "III",
                5 to "V",
                6 to "VI",
                10 to "X",
                11 to "XI",
                17 to "XVII",
                20 to "XX",
                4 to "IV",
                9 to "IX",
                29 to "XXIX"
        )

        for ((arabic, roman) in data) {
            on("when given $arabic") {
                val result = toRoman(arabic)

                it("should return $roman") {
                    assertEquals(roman, result)
                }

            }
        }

    }

    given("A Arabic converter") {

        val data = listOf(
                "I" to 1,
                "II" to 2,
                "V" to 5,
                "VI" to 6,
                "IV" to 4,
                "X" to 10,
                "IX" to 9,
                "XX" to 20,
                "XXIX" to 29
        )

        for ((roman, arabic) in data) {
            on ("when given $roman") {
                val result = toArabic(roman)
                it ("should return $arabic") {
                    assertEquals(arabic, result)
                }
            }
        }
    }

})