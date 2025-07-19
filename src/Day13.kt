private fun part1(input: Map<Int, List<String>>): Int {
    var result = input
//    repeat(10) {
//        result = moveLasers(result)
//        currentLayer = movePlayer(input, currentLayer)
//    }

    val min = input.keys.min()
    val max = input.keys.max()

    for (layer in min..max) {
        val laserIndex = result[layer]?.indexOf("S")
        println("we are in the layer: $layer - laserIndex: $laserIndex - result: $result")
        result = moveLasers(result)
    }
    return 0
}

private fun part2(input: List<String>): Int {
    return 0
}

private fun movePlayer(input: Map<Int, List<String>>, currentLayer: Int): Int {
    val keys = input.keys.toList()
    val nextIndex = (keys.indexOf(currentLayer) + 1) % keys.size
    return keys[nextIndex]
}

private fun parseInput(input: List<String>): Map<Int, List<String>> {
    return buildMap {
        for (line in input) {
            val (layer, range) = line.split(": ").map { it.toInt() }
            put(layer, MutableList(range) { " " }.apply { this[0] = "S" })
        }
    }
}

private fun moveLasers(input: Map<Int, List<String>>): Map<Int, List<String>> {
    return input.mapValues { (_, range) ->
        val current = range.indexOf("S")
        val next = (current + 1) % range.size
        List(range.size) { i ->
            when (i) {
                next -> "S"
                current -> " "
                else -> range[i]
            }
        }
    }
}

fun main() {
    val testInput = parseInput(readInput("Day13_test"))
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

//    val input = readInput("Day13")
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 