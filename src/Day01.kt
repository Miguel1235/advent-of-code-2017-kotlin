private val part = {input: List<Char>, isPart1: Boolean ->
    buildChunks(input, isPart1).filter { it.first == it.second }
        .sumOf { it.first.digitToInt() }
}

private fun buildChunks(input: List<Char>, isPart1: Boolean = true): List<Pair<Char, Char>> {
    if (isPart1) return (input + input.first()).zipWithNext()

    val steps = input.size / 2
    var current = 0
    return buildList {
        while (current < input.size) {
            add(Pair(input[current], input[(current + steps) % input.size]))
            current++
        }
    }
}

fun main() {
    val testInput = readInput("Day01_test").first().toList()
    check(part(testInput, true) == 0)
    check(part(testInput, false) == 4)

    val input = readInput("Day01").first().toList()
    check(part(input, true) == 1102)
    check(part(input, false) == 1076)

}
 