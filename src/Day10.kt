private fun part1(input: List<Int>): Int {
    var circularList = listOf(0,1,2,3,4)
    var skipSize = 0
    var currentPosition = circularList.first()
    for(length in input) {
        println("going to reverse $length elements in the list $circularList starting at: $currentPosition")
        println("size of the list: ${circularList.size} - length: $length - currentPosition: $currentPosition - skipSize: $skipSize")


        val newCircularList = buildList {
//            println("$currentPosition - $length - ${circularList.size}")
            when {
                length == 1 -> addAll(circularList)

                currentPosition + length > circularList.size -> {
                    println("[WRAP] we are going to wrap  more than the list size")
                    val end = circularList.subList(currentPosition, circularList.size)
                    val start = circularList.subList(0, length - end.size)
                    val middle = circularList.subList(start.size, currentPosition)


                    println("start: $start - middle: $middle - end: $end")


                    val reversed = (end+start).reversed()
                    println("reversed: ${(end+start).reversed()}")

                    val newStart = reversed.subList(0, length - end.size)
                    val newEnd = reversed - newStart
                    println("newStart: $newStart - newEnd: $newEnd")

                    addAll(newEnd)
                    addAll(middle)
                    addAll(newStart)
                }

                else -> {
                    addAll(circularList.subList(currentPosition, length).reversed())
                    addAll(circularList.takeLast((length % circularList.size)-1))
                }

            }
        }
        circularList = newCircularList
        println(newCircularList)
//        Move the current position forward by that length plus the skip size.
//        Increase the skip size by one.
//        currentPosition = length + skipSize
        currentPosition = (currentPosition + length + skipSize) % circularList.size
        skipSize++
        println("skips size: $skipSize - currentPosition: $currentPosition")
        println("*********")
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
 