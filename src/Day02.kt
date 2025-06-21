private fun part1(input: List<List<Int>>): Int {
    return input.fold(0) { acc, row -> acc + (row.max() - row.min()) }
}

private fun part2(input: List<List<Int>>): Int {
    return  input.fold(0) { acc, row -> acc + findEvenlyDivisible(row) }
}

private fun findEvenlyDivisible(row: List<Int>): Int {
    for(i in 0 until row.size) {
        for(j in i+1 until row.size) {
            val f = row[i]
            val s = row[j]

            if(f%s == 0) return f/s
            if(s%f == 0) return s/f
        }
    }
    return 0
}

private fun parseInput(input: List<String>): List<List<Int>> {
    return input.map { it.split("""\s+""".toRegex()).map { it.toInt() } }
}

fun main() {
    val testInput = parseInput(readInput("Day02_test"))
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = parseInput(readInput("Day02"))
    check(part1(input) == 41887)
    check(part2(input) == 226)
}
 