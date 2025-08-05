private fun part(startA: Long, startB: Long, isPart1: Boolean): Int {
    var a = startA
    var b = startB

    var judgeCount = 0
    repeat(if(isPart1) 40_000_000 else 5_000_000) {
        a = generateNext(a, true, isPart1)
        b = generateNext(b, false, isPart1)
        if((a and 0xFFFF) == (b and 0xFFFF)) judgeCount++
    }
    return judgeCount
}

private fun generateNext(start: Long, isA: Boolean, isPart1: Boolean): Long {
    val factorA = 16807
    val factorB = 48271
    val mod = 2147483647L
    var result = (start * if(isA) factorA else factorB) % mod

    if(isPart1) return result
    while((isA && result % 4L != 0L) || (!isA && result % 8L != 0L)) {
        result = (result * if(isA) factorA else factorB) % mod
    }
    return result
}

private fun parseInput(input: List<String>) = input.map { it.split("with").last().trim().toLong() }

fun main() {
    val (startATest, startBTest) = parseInput(readInput("Day15_test"))
    check(part(startATest, startBTest, true) == 588)
    check(part(startATest, startBTest, false) == 309)

    val (startA, startB) = parseInput(readInput("Day15"))
    check(part(startA, startB, true) == 626)
    check(part(startA, startB, false) == 306)
}
 