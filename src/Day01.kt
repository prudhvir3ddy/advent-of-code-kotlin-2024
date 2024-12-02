import java.util.PriorityQueue
import kotlin.math.abs


enum class BinarySearchType {
    FIRST,
    LAST
}

fun main() {

    fun part1(input: List<String>): Int {
        val pq1 = PriorityQueue<Int>()
        val pq2 = PriorityQueue<Int>()
        input.forEach {
            val first = it.split("   ")[0].toInt()
            val second = it.split("   ")[1].toInt()
            pq1.add(first)
            pq2.add(second)
        }

        var finalCount = 0

        while (pq1.isNotEmpty() && pq2.isNotEmpty()) {
            val first = pq1.poll()
            val second = pq2.poll()
            finalCount += abs(second - first)
        }
        return finalCount
    }

    fun part1_2(input: List<String>): Int {

        val firstList = input.map {
            it.split("   ")[0].toInt()
        }.toMutableList()

        val secondList = input.map {
            it.split("   ")[1].toInt()
        }.toMutableList()

        firstList.sort()
        secondList.sort()

        var finalCount = 0

        for (i in 0..firstList.lastIndex) {
            finalCount += abs(secondList[i] - firstList[i])
        }

        return finalCount
    }

    fun part2(input: List<String>): Int {

        val firstList = input.map {
            it.split("   ")[0].toInt()
        }

        val secondList = input.map {
            it.split("   ")[1].toInt()
        }.toMutableList()

        secondList.sort()

        var finalCount = 0

        firstList.forEach {
            val first = secondList.binarySearch(it, BinarySearchType.FIRST)
            val second = secondList.binarySearch(it, BinarySearchType.LAST)
            if (first != -1)
                finalCount += it * ((second - first) + 1)
        }

        return finalCount
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
    part1_2(input).println()
}
