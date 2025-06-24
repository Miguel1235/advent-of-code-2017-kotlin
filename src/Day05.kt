private fun part(input: List<Int>, isPart2: Boolean = false): Int {
    var cursor = 0
    val instructions = input.toMutableList()
    var steps = 0

    while(true) {
        val jump =  instructions.getOrNull(cursor)
        if(jump == null) break

        if(jump >= 3 && isPart2) instructions[cursor]-- else instructions[cursor]++
        cursor += jump
        steps++
    }
    return steps
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day05_test").map { it.toInt() }
    check(part(testInput) == 5)
    check(part(testInput, true) == 10)

    val input = readInput("Day05").map { it.toInt() }
    check(part(input) == 372139)
    check(part(input, true) == 29629538)
}
 