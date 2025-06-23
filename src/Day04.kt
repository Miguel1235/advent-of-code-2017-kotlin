private fun part1(input: List<List<String>>): Int {
    return input.fold(0) { acc, curr -> acc + if(curr.toSet().size == curr.size) 1 else 0 }
}

private fun part2(input: List<List<String>>): Int {
    val sortedInput = input.map { line -> line.map { it.toList().sorted().joinToString("") } }
    return part1(sortedInput)
}

private fun parseInput(input: List<String>): List<List<String>> {
    return input.map { it.split("""\s+""".toRegex()) }
}

fun main() {
    val testInput = parseInput(readInput("Day04_test"))
    check(part1(testInput) == 5)
    check(part2(testInput) == 3)

    val input = parseInput(readInput("Day04"))
    check(part1(input) == 477)
    check(part2(input) == 167)
}
 