private fun part1(input: List<HexDirection>): Int {
    val startPosition = HexPosition(0, 0)
    val finalPosition = input.fold(startPosition) { pos, direction ->
        move(pos, direction)
    }
    return manhattanDistance(startPosition, finalPosition)
}

private fun part2(input: List<String>): Int {
    return 0
}

data class HexPosition(val q: Int, val r: Int)
enum class HexDirection(val vector: HexPosition) {
    NORTH(HexPosition(0, -1)),
    SOUTH(HexPosition(0, 1)),
    NORTHWEST(HexPosition(-1, 0)),
    NORTHEAST(HexPosition(1, -1)),
    SOUTHWEST(HexPosition(-1, 1)),
    SOUTHEAST(HexPosition(1, 0))
}

fun manhattanDistance(a: HexPosition, b: HexPosition): Int {
    return (kotlin.math.abs(a.q - b.q) +
            kotlin.math.abs(a.q + a.r - b.q - b.r) +
            kotlin.math.abs(a.r - b.r)) / 2
}

private fun move(pos: HexPosition, direction: HexDirection) = HexPosition(
    pos.q + direction.vector.q,
    pos.r + direction.vector.r,
)

private fun parseInput(input: String) = input.split(",").map {
    when (it.trim()) {
        "n" -> HexDirection.NORTH
        "ne" -> HexDirection.NORTHEAST
        "se" -> HexDirection.SOUTHEAST
        "s" -> HexDirection.SOUTH
        "sw" -> HexDirection.SOUTHWEST
        "nw" -> HexDirection.NORTHWEST
        else -> throw IllegalArgumentException("Unknown direction: $it")
    }
}

fun main() {
    val testInput = parseInput(readInput("Day11_test").first())
    check(part1(testInput) == 3)
//    check(part2(testInput) == 0)

    val input = parseInput(readInput("Day11").first())
    check(part1(input) == 743)
//    check(part2(input) == 0)
}
 