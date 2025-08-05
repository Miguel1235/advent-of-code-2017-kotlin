private fun part1(startA: Long, startB: Long, pairs: Int = 40_000_000): Int {
    var a = startA
    var b = startB

    var judgeCount = 0
    repeat(pairs) {
        a = generateNext(a, true)
        b = generateNext(b, false)
        if((a and 0xFFFF) == (b and 0xFFFF)) judgeCount++
    }
    return judgeCount
}

private fun part2(startA: Long, startB: Long, pairs: Int = 5_000_000): Int {
    var a = startA
    var b = startB
    var judgeCount = 0
    repeat(pairs) {
        a = generateNextMultiple(a, true)
        b = generateNextMultiple(b, false)
        if((a and 0xFFFF) == (b and 0xFFFF)) judgeCount++
    }
    return judgeCount
}

private fun generateNext(start: Long, isA: Boolean): Long {
    val factorA = 16807
    val factorB = 48271
    val mod = 2147483647L
    return (start * if(isA) factorA else factorB) % mod
}

private fun generateNextMultiple(start: Long, isA: Boolean): Long {
    val factorA = 16807
    val factorB = 48271
    val mod = 2147483647L
    var result = (start * if(isA) factorA else factorB) % mod

    while((isA && result % 4L != 0L) || (!isA && result % 8L != 0L)) {
        result = (result * if(isA) factorA else factorB) % mod
    }
    return result
}

private fun parseInput(input: List<String>) = input.map { it.split("with").last().trim().toLong() }

fun main() {
    val (startATest, startBTest) = parseInput(readInput("Day15_test"))
    check(part1(startATest, startBTest) == 588)
    check(part2(startATest, startBTest) == 309)

    val (startA, startB) = parseInput(readInput("Day15"))
    check(part1(startA, startB) == 626)
    check(part2(startA, startB) == 306)
}
 