operator fun String.times(times: Int): String = this.repeat(times)

fun String.countStartsWith(prefix: String): Pair<String, Int> {
    var count = 0
    var buffer = this
    while (buffer.startsWith(prefix)) {
        count++
        buffer = buffer.drop(prefix.length)
    }
    return Pair(buffer, count)
}

private val ROMAN_CONVERSIONS = listOf(
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
)

fun toRoman(input: Int): String = ROMAN_CONVERSIONS.fold(Pair(input, "")) { value, next ->
        val (number, buffer) = value
        val (arabic, roman) = next

        val times: Int = number / arabic
        val remainder: Int = number % arabic

        Pair(remainder, buffer + roman * times)
    }.second


fun toArabic(input: String): Int = ROMAN_CONVERSIONS.fold(Pair(input, 0)) { value, next ->
        val (arabic, roman) = next
        val (buffer, sum) = value

        val (newBuffer, count) = buffer.countStartsWith(roman)

        Pair(newBuffer, sum + (arabic * count))
    }.second
