package simple
fun fizzbuzz(number: Int): String = when {
    number.isDivisible(15) -> "FizzBuzz"
    number.isDivisible(3)-> "Fizz"
    number.isDivisible(5) -> "Buzz"
    else -> number.toString()
}

private fun Int.isDivisible(denominator: Int): Boolean = this % denominator == 0

