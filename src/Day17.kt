private fun part1(steps: Int): Int {
    val buffer = mutableListOf(0)
    var idx = 0
    val iters = 2017

    for (value in 1..iters) {
        idx = (idx + steps) % buffer.size
        idx++
        buffer.add(idx, value)
    }

    val pos = buffer.indexOf(iters)
    return buffer[(pos + 1) % buffer.size]
}

private fun part2(steps: Int): Int {
    var idx = 0
    var size = 1
    var valueAfterZero = 0

    for (value in 1..50_000_000) {
        idx = (idx + steps) % size
        idx++
        if (idx == 1) {
            valueAfterZero = value
        }
        size++
    }
    return valueAfterZero
}

fun main() {
    val testInput = readInput("Day17_test").first().toInt()
    check(part1(testInput) == 638)

    val input = readInput("Day17").first().toInt()
    check(part1(input) == 1173)
    check(part2(input) == 1930815)
}