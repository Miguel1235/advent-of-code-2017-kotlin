private fun part1(input: String, isPart1: Boolean = true): Int {
    val groupStack = mutableListOf<Char>()
    var isInComment = false
    var skipNext = false
    var totalScore = 0
    var groupScore = 0
    var charCount = 0
    for (char in input) {
        if (skipNext) {
            skipNext = false
            continue
        }
        if (char == '!') {
            skipNext = true
            continue
        }
        if (char == '<' && !isInComment) {
            isInComment = true
            continue
        }
        if (char == '<') {
            charCount++
            continue
        }
        if (char == '>' && isInComment) {
            isInComment = false
            continue
        }

        if (isInComment) {
            charCount++
            continue
        }

        if (char == '{') groupStack.add(char)
        if (char == '}') {
            groupStack.removeLast()
            groupScore += groupStack.size + 1
        }
    }
    totalScore += groupScore
    return if (isPart1) totalScore else charCount
}

fun main() {
    val input = readInput("Day09").first()
    check(part1(input) == 13154)
    check(part1(input, false) == 6369)
}
 