package simple

fun bowling(game: String): Int = createFrames(game.toList(), listOf()).sum()


private fun createFrames(game: List<Char>, currentList: List<Int>): List<Int> {

    if (game.size == 0 || currentList.size == 10) {
        return currentList
    }

    val drop = if (isStrike(game)) 1 else 2
    val take = if (isStrike(game) || isSpare(game)) 3 else 2

    return createFrames(game.drop(drop), currentList + convert(game.take(take)))

}

private fun isSpare(game: List<Char>) = game.size > 1 && game[1].equals('/')

private fun isStrike(game: List<Char>) = game.first().equals('X')

private fun convert(rolls: List<Char>): Int = rolls.fold(0) { soFar, next ->
    when (next) {
        'X' -> soFar + 10
        '/' -> (Math.ceil(soFar / 10.0) * 10).toInt()
        '-' -> soFar + 0
        else -> soFar + next.toString().toInt()
    }
}
