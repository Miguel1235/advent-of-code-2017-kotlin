private fun part1(input: List<String>): Int {
    println(input)
    val twoOperandRegex = Regex("""(set|sub|mul|jnz) (\w+) (-?\w+)""")
    val register = mutableMapOf<String, Long>()
    var instructionCounter = 0
    var mulCount = 0

    while (instructionCounter < input.size) {
        val instruction = input[instructionCounter]
        val (operation, op1, op2) = twoOperandRegex.find(instruction)!!.groupValues.drop(1)
        when (operation) {
            "set" -> {
                val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                register[op1] = valueOp2
            }
            "sub" -> {
                val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                register[op1] = valueOp1 - valueOp2
            }
            "mul" -> {
                val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                register[op1] = valueOp1 * valueOp2
                mulCount++
            }
            "jnz" -> {
                val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                if (valueOp1 != 0L) {
                    instructionCounter += (valueOp2).toInt()
                    continue
                }
            }
        }
        instructionCounter++
    }
    return mulCount
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val input = readInput("Day23")
    check(part1(input) == 5929)
}
 