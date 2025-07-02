fun updateRegisterValue(operation: String, registers: MutableMap<String, Int>, register: String, amount: Int) {
    if (operation == "inc") {
        registers[register] = registers[register]!! + amount
        return
    }
    registers[register] = registers[register]!! - amount
}

private fun part1(instructions: List<Instruction>, isPart1: Boolean = true): Int {
    val registers = startRegisters(instructions)
    var maxRegistered = 0
    for(instruction in instructions) {
        val (register, operation, amount, operand1, comparatorOperator, operand2) = instruction
        val operand1Value = registers[operand1]!!
        when(comparatorOperator) {
            ComparatorOperator.EQUAL -> {
                if(operand1Value == operand2) updateRegisterValue(operation, registers, register, amount)
            }
            ComparatorOperator.NOT_EQUAL -> {
                if(operand1Value != operand2) updateRegisterValue(operation, registers, register, amount)

            }
            ComparatorOperator.LESS_THAN -> {
                if(operand1Value < operand2) updateRegisterValue(operation, registers, register, amount)
            }
            ComparatorOperator.LESS_THAN_OR_EQUAL -> {
                if(operand1Value <= operand2) updateRegisterValue(operation, registers, register, amount)

            }
            ComparatorOperator.GREATER_THAN -> {
                if(operand1Value > operand2) updateRegisterValue(operation, registers, register, amount)

            }
            ComparatorOperator.GREATER_THAN_OR_EQUAL -> {
                if(operand1Value >= operand2) updateRegisterValue(operation, registers, register, amount)

            }
        }

        val currentMax = registers.maxOf { it.value }
        if(currentMax > maxRegistered) maxRegistered = currentMax
    }
    return if (isPart1) registers.maxOf { it.value } else maxRegistered
}

enum class ComparatorOperator {
    EQUAL, NOT_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL
}

private fun startRegisters(instructions: List<Instruction>): MutableMap<String, Int> {
    return buildMap {
        for( (register) in instructions) {
            putIfAbsent(register, 0)
        }
    }.toMutableMap()
}

data class Instruction(val register: String, val operation: String, val amount: Int, val operand1: String, val comparatorOperator: ComparatorOperator, val operand2: Int)

private fun parseInput(input: List<String>): List<Instruction> {
    val instructionRegex = Regex("""(\w+) (inc|dec) (-?\d+) if (\w+) (==|>|>=|<|<=|!=) (-?\w+)""")

    return buildList {
        for(instruction in input) {
            val regexResult = instructionRegex.matchEntire(instruction)!!.groupValues.drop(1)
            add(Instruction(regexResult[0], regexResult[1], regexResult[2].toInt(), regexResult[3],when(regexResult[4]) {
                        "==" -> ComparatorOperator.EQUAL
                        ">" -> ComparatorOperator.GREATER_THAN
                        ">=" -> ComparatorOperator.GREATER_THAN_OR_EQUAL
                        "<" -> ComparatorOperator.LESS_THAN
                        "<=" -> ComparatorOperator.LESS_THAN_OR_EQUAL
                        else -> ComparatorOperator.NOT_EQUAL
                }, regexResult[5].toInt()))
        }
    }
}

fun main() {
    val testInput = parseInput(readInput("Day08_test"))
    check(part1(testInput) == 1)
    check(part1(testInput, false) == 10)
     
    val input = parseInput(readInput("Day08"))
    check(part1(input) == 5221)
    check(part1(input, false) == 7491)
}
 