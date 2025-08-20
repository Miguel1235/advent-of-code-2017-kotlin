private fun part1(input: List<String>): Pair<String, Int> {
    var point = Pair(0, input.first().indexOfFirst { it == '|' })
    var direction = Directions.DOWN
    val words = mutableListOf<Char>()
    val wordRegex = Regex("""\w+""")

    var steps = 0
    while (true) {
        steps++
        val (row, col) = point
        if (input[row][col] == '+') {
            for (dir in Directions.entries) {
                val newRow = row + dir.rowDelta
                val newCol = col + dir.colDelta
                val entity = input.getOrNull(newRow)?.getOrNull(newCol)
                if (entity == null || entity == ' ') continue
                if (dir == Directions.UP && direction == Directions.DOWN) continue
                if (dir == Directions.DOWN && direction == Directions.UP) continue
                if (dir == Directions.LEFT && direction == Directions.RIGHT) continue
                if (dir == Directions.RIGHT && direction == Directions.LEFT) continue
                direction = dir
                break
            }
        }
        val newRow = row + direction.rowDelta
        val newCol = col + direction.colDelta
        val entity = input.getOrNull(newRow)?.getOrNull(newCol)
        if (entity == null || entity == ' ') break

        if (entity.toString().contains(wordRegex)) words.add(entity)
        point = Pair(newRow, newCol)
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
 