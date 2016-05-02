package simple
fun calculatePayment(arrival: Int, departure: Int, bedtime: Int): Int {
    val mappedArrival = mapTime(arrival)
    val mappedDeparture = mapTime(departure)

    return (mappedArrival until mappedDeparture).map { determineRate(bedtime, it) }.sum()
}

private fun determineRate(bedtime: Int, it: Int): Int = when {
        it >= 12 -> 20
        it >= bedtime -> 6
        else -> 10
}

private fun mapTime(time :Int): Int = if (time < 5) time + 12 else time