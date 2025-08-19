private fun part1(input: List<String>): Int {
    val startPoint = Pair(0, input.first().indexOfFirst { it == '|' })
    var currentPoint = startPoint
    var currentDirection = Directions.DOWN
    val words = mutableListOf<Char>()


    while(true) {
        val (row, col) = currentPoint
        if(input[row][col] == '+') {
                    for(dir in Directions.entries) {
                        val newRow = row + dir.rowDelta
                        val newCol = col + dir.colDelta
                        val entity = input.getOrNull(newRow)?.getOrNull(newCol)
                        if(entity == null || entity == ' ') continue
                        if(dir == Directions.UP && currentDirection == Directions.DOWN) continue
                        if(dir == Directions.DOWN && currentDirection == Directions.UP) continue
                        if(dir == Directions.LEFT && currentDirection == Directions.RIGHT) continue
                        if(dir == Directions.RIGHT && currentDirection == Directions.LEFT) continue
                        currentDirection = dir
                        break
                    }

        }
        val newRow = row + currentDirection.rowDelta
        val newCol = col + currentDirection.colDelta

        val entity = input.getOrNull(newRow)?.getOrNull(newCol) ?: break
//        println("item: $entity : newRow: $newRow : newCol: $newCol : dir: $currentDirection")

        if(entity.toString().contains(Regex("""\w+"""))) words.add(entity)

        currentPoint = Pair(newRow, newCol)

        println(words.joinToString(""))
    }
    println(words.joinToString(""))
    return 0
}

//private fun part2(input: List<String>): Int {
//    return 0
//}

fun main() {
    val testInput = readInputNoTrim("Day19_test")
    check(part1(testInput) == 0)

    val input = readInputNoTrim("Day19")
    check(part1(input) == 0)
}
 