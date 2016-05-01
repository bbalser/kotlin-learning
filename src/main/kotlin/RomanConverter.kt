operator fun String.times(times: Int): String = this.repeat(times)

val ROMAN_CONVERSIONS = listOf(
        10 to "X",
        9 to "IX",
        5 to "V",
        4 to "IV",
        1 to "I"
)

fun toRoman(arabic: Int): String {

    return ROMAN_CONVERSIONS.fold(Pair(arabic, "")) { value, next ->
        var (number, buffer) = value
        val (arabic, roman) = next

        val times: Int = number / arabic
        val remainder: Int = number % arabic

        Pair(remainder, buffer + roman * times)
    }.second.toString()

}

