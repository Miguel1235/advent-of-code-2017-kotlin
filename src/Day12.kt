import kotlin.reflect.typeOf

class ProgramGraph {
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    fun addNode(node: Int) {
        adjacencyList.putIfAbsent(node, mutableListOf())
    }

    fun addEdge(from: Int, to: Int) {
        addNode(from)
        adjacencyList[from]?.add(to)
    }

    fun dfs(current: Int, target: Int, visited: MutableSet<Int> = mutableSetOf()): Boolean {
        if(current == target) return true
        visited.add(current)
        return adjacencyList[current]?.filterNot { visited.contains(it) }?.any { dfs(it, target, visited) } ?: false
    }
}

private fun part1(input: List<String>): Int {
    val programGraph = ProgramGraph()

    for(program in input) {
        val (from, to) = program.split("<->").map { it.trim() }

        if(to.contains(",")) {
            to.split(",").map { it.trim() }.forEach {
                programGraph.addEdge(from.toInt(), it.toInt())
            }
        } else {
            programGraph.addEdge(from.toInt(), to.toInt())
        }
    }
    return programGraph.adjacencyList.keys.count { programGraph.dfs(0, it) }
}

fun main() {
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 6)

    val input = readInput("Day12")
    check(part1(input) == 141)
}
 