private fun part1(gridMap: MutableMap<Pair<Int, Int>, Char>): Int {
    var point = Pair(gridMap.keys.maxOf { it.first } / 2, gridMap.keys.maxOf { it.second } / 2)
    var direction = Directions.UP
    var infections = 0

    repeat(10_000) {
        val (r, c) = point
        val node = gridMap.getOrElse(Pair(r, c)) { '.' }
        if (node == '.') {
            direction = when(direction) {
                Directions.UP -> Directions.LEFT
                Directions.LEFT -> Directions.DOWN
                Directions.RIGHT -> Directions.UP
                Directions.DOWN -> Directions.RIGHT
            }
            gridMap[point] = '#'
            infections++
        } else {
            direction = when(direction) {
                Directions.UP -> Directions.RIGHT
                Directions.LEFT -> Directions.UP
                Directions.RIGHT -> Directions.DOWN
                Directions.DOWN -> Directions.LEFT
            }
            gridMap[point] = '.'
        }
        point = Pair(r + direction.rowDelta, c + direction.colDelta)
    }
    return infections
}

private fun part2(gridMap: MutableMap<Pair<Int, Int>, Char>): Int {
    var point = Pair(gridMap.keys.maxOf { it.first } / 2, gridMap.keys.maxOf { it.second } / 2)
    var direction = Directions.UP
    var infections = 0

    repeat(10_000_000) {
        val (r, c) = point
        val node = gridMap.getOrElse(Pair(r, c)) { '.' }

        when(node) {
            '.' -> {
                direction = when(direction) {
                    Directions.UP -> Directions.LEFT
                    Directions.LEFT -> Directions.DOWN
                    Directions.RIGHT -> Directions.UP
                    Directions.DOWN -> Directions.RIGHT
                }
                gridMap[point] = 'W'
            }
            'W' -> {
                infections++
                gridMap[point] = '#'
            }
            '#' -> {
                direction = when(direction) {
                    Directions.UP -> Directions.RIGHT
                    Directions.LEFT -> Directions.UP
                    Directions.RIGHT -> Directions.DOWN
                    Directions.DOWN -> Directions.LEFT
                }
                gridMap[point] = 'F'
            }
            'F' -> {
                direction = when(direction) {
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

private fun parseInput(input: List<String>): MutableMap<Pair<Int, Int>, Char> {
    return buildMap {
        for(r in 0..<input.size) {
            for(c in 0..<input.size) {
                put(Pair(r, c), input[r][c])
            }
        }
    }.toMutableMap()
}

fun main() {
    val testInput = parseInput(readInput("Day22_test"))
    check(part1(testInput) == 5587)
    check(part2(testInput) == 2511944)

    val input = parseInput(readInput("Day22"))
    check(part1(input) == 5348)
    check(part2(input) == 2512225)

}
 