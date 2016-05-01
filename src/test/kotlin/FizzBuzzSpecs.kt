import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

class FizzBuzzSpecs: Spek({

    given("A FizzBuzzer") {
        on("when invoked") {
            val data = listOf(
                    1 to "1",
                    2 to "2",
                    3 to "Fizz",
                    5 to "Buzz",
                    6 to "Fizz",
                    10 to "Buzz",
                    15 to "FizzBuzz",
                    30 to "FizzBuzz"
            )

            for ((input, output) in data) {
                it("should return $output when given $input") {
                    assertEquals(output, fizzbuzz(input))
                }
            }
        }
    }

})