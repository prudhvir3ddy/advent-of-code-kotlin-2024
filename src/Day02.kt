import kotlin.math.abs

fun main() {

    fun checkIfInLimits(entry1: Int, entry2: Int): Boolean {
        return abs(entry1 - entry2) in 1..3
    }

    fun isSafeReport(report: List<Int>): Boolean {
        val incReport = report.sorted()
        val decReport = report.sortedDescending()

        val flag = report == incReport || report == decReport
        if (flag) {
            for (i in 0 until report.lastIndex) {
                if (!checkIfInLimits(report[i], report[i + 1])) {
                    return false
                }
            }
        }

        return flag
    }


    fun part1(input: List<String>): Int {
        var safeReportCount = 0
        for (data in input) {
            val report = data.split(" ").map { it.toInt() }
            if (isSafeReport(report))
                safeReportCount++
        }
        return safeReportCount
    }

    fun part2(input: List<String>): Int {
        var safeReportCount = 0
        for (data in input) {
            val report = data.split(" ").map { it.toInt() }
            if (isSafeReport(report)) {
                safeReportCount++
            } else {
                for (i in 0..report.lastIndex) {
                    val newReport = report.toMutableList()
                    newReport.removeAt(index = i)
                    if (isSafeReport(newReport)) {
                        safeReportCount++
                        break
                    }
                }
            }
        }
        return safeReportCount
    }


    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}