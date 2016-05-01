import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class RomanSpec: Spek({

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

})