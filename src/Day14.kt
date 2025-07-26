private fun part1(input: String): Int {
    println("The input is $input")
    val result = buildList {
        for(i in 0..127) {
            add("$input-$i")
        }
    }

    println(result)
    return 0
}

private fun part2(input: String): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day14_test").first()
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)
     
    val input = readInput("Day14").first()
    check(part1(input) == 0)
    check(part2(input) == 0)
}
 