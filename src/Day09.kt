private fun part1(input: String, isPart1: Boolean = true): Int {
    data class ProcessingState(
        val score: Int = 0,
        val charCount: Int = 0,
        val skipNext: Boolean = false,
        val isInComment: Boolean = false,
        val groupStack: MutableList<Char> = mutableListOf()
    )

    val (score, charCount) = input.fold(ProcessingState()) { state, char ->
        when {
            state.skipNext -> state.copy(skipNext = false)
            char == '!' -> state.copy(skipNext = true)
            char == '<' && !state.isInComment -> state.copy(isInComment = true)
            char == '<' -> state.copy(charCount = state.charCount + 1)
            char == '>' && state.isInComment -> state.copy(isInComment = false)
            state.isInComment -> state.copy(charCount = state.charCount + 1)
            char == '{' -> {
                state.groupStack.add(char)
                state
            }

            char == '}' -> {
                state.groupStack.removeLast()
                state.copy(score = state.score + state.groupStack.size + 1)
            }

            else -> state
        }
    }
    return if (isPart1) score else charCount
}

fun main() {
    val input = readInput("Day09").first()
    check(part1(input) == 13154)
    check(part1(input, false) == 6369)
}
 