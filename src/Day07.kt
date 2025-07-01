private fun part1(input: List<String>): Int {
    data class Tower(val name: String, val size: Int, val children: List<Tower>?) {
        fun weight(): Int {
            return children?.sumOf { 1 + it.weight() } ?: 0
        }
    }
    val allTowers = mutableListOf<Tower>()

    val towerRegex = Regex("""^(\w+) \((\d+)\)(?: -> ([\w, ]+))?$""")
    repeat(10) {
        for (tower in input) {
            val (tower, size, children) = towerRegex.matchEntire(tower)!!.groupValues.drop(1)

            if (children.isEmpty() && !allTowers.any { it.name == children }) {
                allTowers.add(Tower(tower, size.toInt(), null))
                continue
            }

            val childrenTowerList = mutableListOf<Tower>()
            for (child in children.split(", ")) {
                val isInList = allTowers.any { it.name == child }
                if (!isInList) {
                    childrenTowerList.add(Tower(child, 0, null))
                } else {
                    val childTower = allTowers.first { it.name == child }
                    childrenTowerList.add(childTower)
                }
            }
            allTowers.add(Tower(tower, size.toInt(), childrenTowerList))
        }
    }


//    allTowers.forEach { println("${it.name} ${it.weight()}") }

    val baseTower = allTowers.maxBy { it.weight() }
    println(baseTower.name)
    println("*****")
    return 0
}

private fun part2(input: List<String>): Int {
    return 0
}

fun main() {
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

    val input = readInput("Day07")
    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 