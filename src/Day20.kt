import kotlin.math.abs

private fun part1(input: List<Particle>, isPart2: Boolean = false): Int {
    val distancesMap: MutableMap<Int, Int> = mutableMapOf()
    var particles = input.map { it.copy()}
    repeat(10_000) {
        particles.forEach{ it.tick() }
        val idx = minDistance(particles)
        distancesMap.putIfAbsent(idx, 0)
        distancesMap[idx] = distancesMap[idx]!! + 1

        if(isPart2) particles = particles.groupBy { it.p }.filter { it.value.size == 1 }.values.flatten()
    }
    return if(isPart2) particles.size else distancesMap.maxBy { it.value }.key
}

private fun minDistance(particles: List<Particle>): Int {
    val r = particles.minBy { particle -> particle.p.toList().sumOf { abs(it)} }
    return particles.indexOf(r)
}

data class Particle(var p: Triple<Int, Int, Int>, var v: Triple<Int, Int, Int>, val a: Triple<Int, Int, Int>) {
    fun tick() {
        v = Triple(v.first + a.first, v.second + a.second, v.third + a.third)
        p = Triple(p.first + v.first, p.second + v.second, p.third + v.third)
    }
}

private fun parseInput(input: List<String>): List<Particle> {
    return buildList {
        for(particle in input) {
            val (p, v, a) = particle.split(", ")
            add(Particle(
                obtainValues(p),
                obtainValues(v),
                obtainValues(a)
            ))
        }
    }
}

private fun obtainValues(i: String): Triple<Int, Int, Int> {
    val particleRegex = Regex("""-?\d+""")
    val (x,y,z) = particleRegex.findAll(i).map { it.value.toInt() }.toList()
    return Triple(x,y,z)
}


fun main() {
    val testInput = parseInput(readInput("Day20_test"))
    check(part1(testInput) == 0)

    val input = parseInput(readInput("Day20"))
    check(part1(input) == 300)
    check(part1(input, true) == 502)
}
 