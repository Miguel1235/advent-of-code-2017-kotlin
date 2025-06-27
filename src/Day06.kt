private fun part1(input: List<Int>): Long {
    val banks = input.toMutableList()
    val maxIndex = banks.indexOf(input.max())
    val blocks2Distribute = banks[maxIndex]

    val first = distribute(maxIndex, blocks2Distribute, banks).toList()
    var second = first.toMutableList()

    var amount = 1L

    while(true) {
        val maxIndex = second.indexOf(second.max())
        val blocks2Distribute = second[maxIndex]
        second = distribute(maxIndex, blocks2Distribute, second)
        amount++
        println("first: $first - second: $second - amount: $amount")
        if(first == second) break
    }
    return amount
}

private fun distribute(maxIndex: Int, blocks2Distribute: Int, banks: MutableList<Int>): MutableList<Int> {
    banks[maxIndex] = 0
    var blocks2Distribute1 = blocks2Distribute
    var currentIndex = maxIndex
    while (blocks2Distribute1 > 0) {
        currentIndex = (currentIndex + 1) % banks.size
        banks[currentIndex] += 1
        blocks2Distribute1--
    }
    return banks
}

private fun part2(input: List<Int>): Int {
    return 0
}

private fun parseInput(input: List<String>) = input.first().split("""\s+""".toRegex()).map { it.toInt() }

fun main() {
    val testInput = parseInput(readInput("Day06_test"))
    check(part1(testInput) == 5L)
//    check(part2(testInput) == 0)
     
    val input = parseInput(readInput("Day06"))
    check(part1(input) == 0L)
//    check(part2(input) == 0)
}
 