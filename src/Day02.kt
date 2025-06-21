private val findEvenlyDivisible = { row: List<Int> ->
    row.indices.asSequence()
        .flatMap { i -> (i + 1 until row.size).map { j -> row[i] to row[j] } }
        .firstNotNullOfOrNull { (first, second) ->
            when {
                first % second == 0 -> first / second
                second % first == 0 -> second / first
                else -> null
            }
        } ?: 0
}

private val parseInput = { input: List<String> -> input.map { it.split("""\s+""".toRegex()).map { it.toInt() } } }

private val part1 = { input: List<List<Int>> -> input.sumOf { it.max() - it.min() } }
private val part2 = { input: List<List<Int>> -> input.sumOf { findEvenlyDivisible(it) } }


fun main() {
    val testInput = parseInput(readInput("Day02_test"))
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = parseInput(readInput("Day02"))
    check(part1(input) == 41887)
    check(part2(input) == 226)
}
 