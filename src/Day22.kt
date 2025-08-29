private fun part(inputMap: Map<Pair<Int, Int>, Char>, isPart1: Boolean = true): Int {
    val gridMap = inputMap.toMutableMap()
    var point = Pair(gridMap.keys.maxOf { it.first } / 2, gridMap.keys.maxOf { it.second } / 2)
    var direction = Directions.UP
    var infections = 0
    repeat(if (isPart1) 10_000 else 10_000_000) {
        val (r, c) = point
        val node = gridMap.getOrElse(Pair(r, c)) { '.' }
        when (node) {
            '.' -> {
                direction = when (direction) {
                    Directions.UP -> Directions.LEFT
                    Directions.LEFT -> Directions.DOWN
                    Directions.RIGHT -> Directions.UP
                    Directions.DOWN -> Directions.RIGHT
                }
                if (isPart1) {
                    gridMap[point] = '#'
                    infections++
                } else gridMap[point] = 'W'
            }
            'W' -> {
                infections++
                gridMap[point] = '#'
            }
            '#' -> {
                direction = when (direction) {
                    Directions.UP -> Directions.RIGHT
                    Directions.LEFT -> Directions.UP
                    Directions.RIGHT -> Directions.DOWN
                    Directions.DOWN -> Directions.LEFT
                }
                gridMap[point] = if (isPart1) '.' else 'F'
            }
            'F' -> {
                direction = when (direction) {
                    Directions.UP -> Directions.DOWN
                    Directions.LEFT -> Directions.RIGHT
                    Directions.RIGHT -> Directions.LEFT
                    Directions.DOWN -> Directions.UP
                }
                gridMap[point] = '.'
            }
        }
        point = Pair(r + direction.rowDelta, c + direction.colDelta)
    }
    return infections
}

private fun parseInput(input: List<String>): Map<Pair<Int, Int>, Char> {
    return buildMap {
        input.forEachIndexed { r, line ->
            line.forEachIndexed { c, ch ->
                put(r to c, ch)
            }
        }
    }
}

fun main() {
    val testInput = parseInput(readInput("Day22_test"))
    check(part(testInput) == 5587)
    check(part(testInput, false) == 2511944)

    val input = parseInput(readInput("Day22"))
    check(part(input) == 5348)
    check(part(input, false) == 2512225)
}
 