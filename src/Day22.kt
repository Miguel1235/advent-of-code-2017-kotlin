private fun part1(input: List<List<Char>>): Int {
    var point = Pair(input.size / 2, input[0].size / 2)
    val gridMap = buildMap {
        for(r in 0..<input.size) {
            for(c in 0..<input[0].size) {
                put(Pair(r, c), input[r][c])
            }
        }
    }.toMutableMap()

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

fun main() {
    val testInput = readInput("Day22_test").map { it.toList() }
    check(part1(testInput) == 5587)

    val input = readInput("Day22").map { it.toList() }
    part1(input).println()
    check(part1(input) == 5348)
}
 