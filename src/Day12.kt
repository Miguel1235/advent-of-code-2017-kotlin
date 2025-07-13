class ProgramGraph(input: List<String>) {
    val adjacencyList = mutableMapOf<Int, MutableList<Int>>()
    init {
        for(program in input) {
            val (from, to) = program.split("<->").map { it.trim() }

            if(to.contains(",")) {
                to.split(",").map { it.trim() }.forEach {
                    addEdge(from.toInt(), it.toInt())
                }
            } else {
                addEdge(from.toInt(), to.toInt())
            }
        }
    }
    fun addEdge(from: Int, to: Int) {
        adjacencyList.putIfAbsent(from, mutableListOf())
        adjacencyList[from]?.add(to)
    }
    fun dfs(current: Int, target: Int, visited: MutableSet<Int> = mutableSetOf()): Boolean {
        if(current == target) return true
        visited.add(current)
        return adjacencyList[current]?.filterNot { visited.contains(it) }?.any { dfs(it, target, visited) } ?: false
    }
}

private val part1 = { graph: ProgramGraph -> graph.adjacencyList.keys.count { graph.dfs(0, it) } }

fun main() {
    val testInput = ProgramGraph(readInput("Day12_test"))
    check(part1(testInput) == 6)

    val input = ProgramGraph(readInput("Day12"))
    check(part1(input) == 141)
}
 