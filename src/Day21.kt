data class Rule(val from: List<List<Char>>, val to: List<List<Char>>)


fun <T> splitGrid(grid: List<List<T>>, parts: Int): List<List<List<T>>> {
    val rows = grid.size
    val cols = grid[0].size
    val root = kotlin.math.sqrt(parts.toDouble()).toInt()

    require(root * root == parts) { "Parts must be a perfect square (e.g. 4, 9, 16...)" }
    require(rows % root == 0 && cols % root == 0) {
        "Grid dimensions must be divisible by sqrt(parts)"
    }

    val rowStep = rows / root
    val colStep = cols / root

    val result = mutableListOf<List<List<T>>>()

    for (blockRow in 0 until root) {
        for (blockCol in 0 until root) {
            val subGrid = grid
                .subList(blockRow * rowStep, (blockRow + 1) * rowStep)
                .map { it.subList(blockCol * colStep, (blockCol + 1) * colStep) }
            result.add(subGrid)
        }
    }
    return result
}


private fun part1(rules: List<Rule>): Int {
    var start = mutableListOf(".#.", "..#", "###").map { it.toList() }

    val rule2Apply = rules.find { it.from == start }
    // divide the start grid
    start = rule2Apply!!.to.map { it.toList() }


    val isDivBy2 = start[0].count() % 2 == 0
    if(isDivBy2) {
        println(start)
        println(splitGrid(start, 4))
//        println(start.split)
//        println(start.windowed(2, 2))
//        for(r in 0..<2) {
//            for(c in 0..<2) {
//                println("r: $r, c: $c - ${start[r][c]}")
////                println(start[i][j])
//            }
//            println()
//        }
//        println("going to divide the start by two")
//        val r = start.take(2)
//        println(r.map { it.take(2) })
    }

    return 0
}

private fun parseInput(rules: List<String>) = rules.map {
    val (from, to) = it.split(" => ").map { it.split("/") }
    Rule(from.map {it.toList()}, to.map {it.toList()})
}

private fun prettyPrint(rule: List<String>) {
    rule.forEach {println(it)}
    println()
}

fun main() {
    val testInput = parseInput(readInput("Day21_test"))
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
     
//    val input = readInput("Day21")
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 