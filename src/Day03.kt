fun main() {

    fun matchesPrefix(data: String, i: Int): Boolean {
        return i + 3 <= data.lastIndex &&
            data[i] == 'm' &&
            data[i + 1] == 'u' &&
            data[i + 2] == 'l' &&
            data[i + 3] == '('
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
                if (matchesPrefix(data, i)) {
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

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
//    part2(input).println()
}