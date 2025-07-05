private fun part1(input: String, isPart1: Boolean = true): Int {
    val groupStack = mutableListOf<Char>()
    var isInComment = false
    var skipNext = false
    var score = 0
    var charCount = 0

    for (char in input) {
        when {
            skipNext -> skipNext = false
            char == '!' -> skipNext = true
            char == '<' && !isInComment -> isInComment = true
            char == '<' -> charCount++
            char == '>' && isInComment -> isInComment = false
            isInComment -> charCount++
            char == '{' -> groupStack.add(char)
            char == '}' -> {
                groupStack.removeLast()
                score += groupStack.size + 1
            }
        }
    }
    return if (isPart1) score else charCount
}

fun main() {
    val input = readInput("Day09").first()
    check(part1(input) == 13154)
    check(part1(input, false) == 6369)
}
 