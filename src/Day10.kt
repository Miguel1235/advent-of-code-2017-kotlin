private fun part1(input: List<Int>): Int {
     var circularList = listOf(0,1,2,3,4)
    var skipSize = 0
    var currentPosition = circularList.first()
    for(length in input) {
        println("going to reverse $length elements in the list $circularList starting at: $currentPosition")
        println(circularList.subList(currentPosition, length).reversed())
        val newCircularList = buildList {
            addAll(circularList.subList(currentPosition, length).reversed()) // wrap around it
            addAll(circularList.takeLast((length % circularList.size)-1)) // this should be changed specif to the wraps
        }
        circularList = newCircularList
        println(newCircularList)
//        Move the current position forward by that length plus the skip size.
//        Increase the skip size by one.
        currentPosition = length + skipSize
        skipSize++
        println("skips size: $skipSize - currentPosition: $currentPosition")
    }
    return 0
}

//private fun part2(input: List<String>): Int {
//    return 0
//}

private fun parseInput(input: List<String>) = input.first().split(",").map { it.trim().toInt() }

fun main() {
    val testInput = parseInput(readInput("Day10_test"))
    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)
     
//    val input = readInput("Day10")
//    check(part1(input) == 0)
//    check(part2(input) == 0)
}
 