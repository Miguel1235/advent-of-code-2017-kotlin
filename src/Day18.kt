private fun part1(input: List<String>): Long {
    val oneOperandRegex = Regex("""(rcv|snd) (\w+)""")
    val twoOperandRegex = Regex("""(set|add|mul|mod|jgz) (\w+) (-?\w+)""")
    val register = mutableMapOf<String, Long>()
    val playedSounds = mutableListOf<Long>()
    var instructionCounter = 0

    while(instructionCounter < input.size) {
        val instruction = input[instructionCounter]
        val oneOpRegex = oneOperandRegex.find(instruction)?.groupValues
        if(oneOpRegex != null) {
            val (op, reg) = oneOpRegex.drop(1)
            when(op) {
                "rcv" -> {
                    if(register[reg] != null && register[reg] != 0L) {
                        return playedSounds.last()
                    }
                }
                "snd" -> {
                    val valueOp2 = register[reg] ?: (reg.toLongOrNull() ?: 0)
                    playedSounds.add(valueOp2)
                }
            }
        } else {
            val (operation, op1, op2) = twoOperandRegex.find(instruction)!!.groupValues.drop(1)
            when(operation) {
                "set" -> {
                    val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                    register[op1] = valueOp2
                }
                "add" -> {
                    val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                    val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                    register[op1] = valueOp1 + valueOp2
                }
                "mul" -> {
                    val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                    val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                    register[op1] = valueOp1 * valueOp2
                }
                "mod" -> {
                    val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                    val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                    register[op1] = valueOp1 % valueOp2
                }
                "jgz" -> {
                    val valueOp1 = register[op1] ?: (op1.toLongOrNull() ?: 0)
                    val valueOp2 = register[op2] ?: (op2.toLongOrNull() ?: 0)
                    if(valueOp1 > 0) {
                        instructionCounter += (valueOp2).toInt()
                        continue
                    }
                }
            }
        }
        instructionCounter++
    }
    return 0L
}

fun main() {
    val testInput = readInput("Day18_test")
    check(part1(testInput) == 4L)

    val input = readInput("Day18")
    check(part1(input) == 1187L)
}
 