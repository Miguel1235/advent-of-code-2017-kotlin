private fun part1(input: List<String>): Pair<String, Int> {
    val startPoint = Pair(0, input.first().indexOfFirst { it == '|' })
    var currentPoint = startPoint
    var currentDirection = Directions.DOWN
    val words = mutableListOf<Char>()

    var steps = 0
    while(true) {
        steps++
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

        if(entity == ' ') {
            break
        }

        if(entity.toString().contains(Regex("""\w+"""))) words.add(entity)

        currentPoint = Pair(newRow, newCol)
    }
    return Pair(words.joinToString(""), steps)
}

fun main() {
    val testInput = readInputNoTrim("Day19_test")
    val testResult = part1(testInput)
    check(testResult.first == "ABCDEF")
    check(testResult.second == 38)

    val input = readInputNoTrim("Day19")
    val inputResult = part1(input)
    check(inputResult.first == "PVBSCMEQHY")
    check(inputResult.second == 17736)
}
 