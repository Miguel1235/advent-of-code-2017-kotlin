import kotlin.math.abs
import kotlin.math.max

private fun part1(input: Int): Int {
    return findPosition(input).sum()
}

private fun part2(input: List<String>): Int {
    return 0
}

data class Position(val r: Int, val c: Int) {
    fun sum() = abs(r) + abs(c)
}

fun findPosition(number: Int): Position {
    if (number == 1) return Position(0, 0)

    var layer = 1
    var maxInLayer = 1
    while (number > maxInLayer) {
        maxInLayer += 8 * layer
        layer++
    }
    layer--

    val startOfLayer = maxInLayer - 8 * layer + 1
    val posInLayer = number - startOfLayer
    val sideLength = 2 * layer
    val side = posInLayer / sideLength
    val posOnSide = posInLayer % sideLength

    return when (side) {
        0 -> Position(layer, -layer + 1 + posOnSide)
        1 -> Position(layer - 1 - posOnSide, layer)
        2 -> Position(-layer, layer - 1 - posOnSide)
        3 -> Position(-layer + 1 + posOnSide, -layer)
        else -> throw IllegalStateException("Invalid side: $side")
    }
}

fun main() {
    val testInput = readInput("Day03_test").first().toInt()
    check(part1(testInput) == 31)
     
    val input = readInput("Day03").first().toInt()
    check(part1(input) == 480)
}
 