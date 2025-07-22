private fun part1(input: List<Laser>): Int {
    var result = input
    val min = input.minOf { it.layer }
    val max = input.maxOf { it.layer }

    var total = 0
    for(i in min..max) {
        result.firstOrNull { it.layer == i }?.let {
            if(it.current == 0) {
                total += it.layer * (it.size+1)
            }
        }
        result = moveLasers(result)
    }
    return total
}

enum class Direction { FORWARD, BACKWARD }
data class Laser(val layer: Int, val current: Int, val size: Int, val direction: Direction) {
    override fun toString(): String {
        return buildString {
            for (i in 0..size) {
                append(if (i == current) "S" else ".")
            }
        }
    }
}

private fun parseInput(input: List<String>): List<Laser> {
    return buildList {
        for (line in input) {
            val (layer, range) = line.split(": ").map { it.toInt() }
            add(Laser(layer, 0, range-1, Direction.FORWARD))
        }
    }
}

private fun moveLasers(input: List<Laser>): List<Laser> {
    return buildList {
        for(laser in input) {
            val updateCurrent = if (laser.direction == Direction.FORWARD) laser.current + 1 else laser.current - 1
            val (newDirection, newCurrent) = when {
                updateCurrent >= laser.size -> Pair(Direction.BACKWARD, laser.size)
                updateCurrent <= 0 -> Pair(Direction.FORWARD,0)
                else -> Pair(laser.direction, updateCurrent)
            }
            add(laser.copy(direction = newDirection, current = newCurrent))
        }
    }
}

fun main() {
    val testInput = parseInput(readInput("Day13_test"))
    check(part1(testInput) == 24)

    val input = parseInput(readInput("Day13"))
    check(part1(input) == 3184)
//    check(part2(input) == 0)
}
 