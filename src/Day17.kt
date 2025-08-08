private fun part(steps: Int, moment: Int = 2017, part2: Boolean = false): Int {
    val start = mutableListOf(0)
    var currentIdx = 0
    var currentValue = 1

    repeat(moment) {
        repeat(steps) {
            currentIdx = (currentIdx + 1) % start.size
        }
        currentIdx++
        start.add(currentIdx, currentValue)
        currentValue++
    }

    return if(part2) start[start.indexOf(0)+1] else start[start.indexOf(2017)+1]
}

fun main() {
    val testInput = readInput("Day17_test").first().toInt()
    check(part(testInput) == 638)
//    check(part2(testInput) == 0)
     
    val input = readInput("Day17").first().toInt()
    check(part(input) == 1173)
    part(input, 50_000_000, true)
}
 