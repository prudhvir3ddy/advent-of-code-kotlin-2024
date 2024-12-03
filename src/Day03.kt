fun main() {

    fun matchesPrefix(data: String, i: Int, prefix: String): Boolean {
        var i  = i
        var j = 0
        while (i <= data.lastIndex && j <= prefix.lastIndex && data[i] == prefix[j]) {
            i++
            j++
        }

        return j == prefix.length
    }

    fun getSuffixResult(data: String, i: Int): Pair<Boolean, Int> {
        if (i > data.lastIndex || data[i].isDigit().not())
            return Pair(false, -1)

        var first = ""
        var second = ""
        var i = i

        while (i <= data.lastIndex && data[i].isDigit()) {
            first += data[i]
            i++
        }

        if (i <= data.lastIndex && data[i] == ',') {
            i++
            while (i<= data.lastIndex && data[i].isDigit()) {
                second += data[i]
                i++
            }
        } else {
            return Pair(false, -1)
        }

        if (i <= data.lastIndex && data[i] == ')') {
            return Pair(true, first.toInt() * second.toInt())
        }

        return Pair(false, -1)
    }

    fun part1(input: List<String>): Long {
        var result = 0L
        input.forEach { data ->
            var i = 0
            while (i <= data.lastIndex) {
                if (matchesPrefix(data, i, "mul(")) {
                    val suffixResult = getSuffixResult(data, i + 4)
                    if (suffixResult.first) {
                        result += suffixResult.second.toLong()
                    }
                }
                i++
            }
        }
        return result
    }

    fun checkFlagInData(flag: Boolean, data: String, i: Int): Boolean {
        if (matchesPrefix(data, i, "don't()"))
            return false
        if (matchesPrefix(data, i, "do()"))
            return true
        return flag
    }

    fun part2(input: List<String>): Long {
        var result = 0L
        var shouldDo = true
        input.forEach { data->
            var i = 0
            while (i <= data.lastIndex) {
                shouldDo = checkFlagInData(shouldDo, data, i)
                if (shouldDo && matchesPrefix(data, i, "mul(")) {
                    val suffixResult = getSuffixResult(data, i + 4)
                    if (suffixResult.first) {
                        result += suffixResult.second.toLong()
                    }
                }
                i++
            }
        }
        return result
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println() // 173731097
    part2(input).println()
}