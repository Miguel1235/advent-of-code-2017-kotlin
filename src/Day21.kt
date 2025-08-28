fun <T> flipHorizontal(grid: List<List<T>>): List<List<T>> = grid.map { it.asReversed() }
fun <T> flipVertical(grid: List<List<T>>): List<List<T>> = grid.asReversed()
fun <T> flipBoth(grid: List<List<T>>): List<List<T>> = grid.asReversed().map { it.asReversed() }
fun <T> transpose(grid: List<List<T>>): List<List<T>> =
    grid[0].indices.map { c -> grid.indices.map { r -> grid[r][c] } }

fun <T> rotate90(grid: List<List<T>>): List<List<T>> = transpose(grid.asReversed())
fun <T> rotate180(grid: List<List<T>>): List<List<T>> = flipBoth(grid)
fun <T> rotate270(grid: List<List<T>>): List<List<T>> = transpose(grid).asReversed()

data class Rule(val from: List<List<Char>>, val to: List<List<Char>>) {
    fun allSymmetries(): List<List<List<Char>>> = listOf(
        from,
        flipHorizontal(from),
        rotate90(from),
        rotate180(from),
        rotate270(from),
        flipVertical(from),
        flipHorizontal(rotate90(from)),
        flipVertical(rotate90(from))
    )
}


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
    if (isDivBy2) {
        val enhancedGrid = splitGrid(start, start.size)
        val result = buildList {
            for (square in enhancedGrid) {
                rules.find { rule -> rule.allSymmetries().any { it == square } }?.let {
                    add(it.to)
                }
            }
        }


        val r = buildList {
            for (i in 0..<result.size step 2) {
                for(a in i..<i+2) {
                    for(b in 0..<result[0].size) {
                        add(result[a][b]+result[a][b])
                    }
                }
            }
        }

        prettyPrint(r)




//        val a = buildList {
//            for(a in 0..<result[0].size) {
//                add(result[0][a]+result[1][a])
//            }
//        }
//        prettyPrint(a)
//
//        val b = buildList {
//            for(a in 0..<result[0].size) {
//                add(result[2][a]+result[3][a])
//            }
//        }
//        prettyPrint(b)
//
//
//        prettyPrint(a+b)

//        val grouped = result.flatten().chunked(3)
//        for(r in grouped) {
//            println(r)
//        }

//        prettyPrint(result.flatten().flatten().chunked((start.size/2)*3))
    }

    return 0
}

private fun parseInput(rules: List<String>) = rules.map {
    val (from, to) = it.split(" => ").map { it.split("/") }
    Rule(from.map { it.toList() }, to.map { it.toList() })
}



fun main() {
    val testInput = parseInput(readInput("Day21_test"))
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

//    val input = readInput("Day21")
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 