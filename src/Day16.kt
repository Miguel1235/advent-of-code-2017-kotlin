private fun part1(instructions: List<String>, times: Int = 1): String {
    var programs = listOf('a','b','c','d','e', 'f', 'g', 'h','i','j','k','l','m','n','o','p')
    repeat(times) {
        for(instruction in instructions) {
            if(instruction.startsWith("s")) {
                val steps = instruction.substringAfter("s").toInt()
                programs = programs.rotateLeft(steps)
            }
            if(instruction.startsWith("x")) {
                val (a,b) = instruction.substringAfter("x").split("/").map { it.trim().toInt() }
                programs  = programs.toMutableList().apply {
                    set(a, programs[b])
                    set(b, programs[a])
                }
            }
            if(instruction.startsWith("p")) {
                val (a,b) = instruction.substringAfter("p").split("/").map { it.trim()[0] }
                val p1 = programs.toMutableList().indexOf(a)
                val p2 = programs.toMutableList().indexOf(b)
                programs  = programs.toMutableList().apply {
                    set(p1, b)
                    set(p2, a)
                }
            }
        }
    }

    return programs.joinToString("")
}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.trim() }

fun main() {
    val testInput = parseInput(readInput("Day16_test"))
    check(part1(testInput) == "paedcbfghijklmno")

    val input = parseInput(readInput("Day16"))
    check(part1(input) == "iabmedjhclofgknp")
    part1(input, 1_000_000_000).println()
}
 