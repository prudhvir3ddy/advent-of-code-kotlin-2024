private fun buildMatrix(input: List<String>): List<List<Char>> {
    return input.map { it.toCharArray().toList() }
}

private fun traverseRight(matrix: List<List<Char>>, i: Int, j: Int): Int {
    val hashSet= mutableSetOf<Char>()
    hashSet.add(matrix[i][j])
    for (k in 1..3) {
        if (j + k > matrix[0].lastIndex) {
            return 0
        }
        val current = matrix[i][j + k]
        if (hashSet.contains(current)) {
            return 0
        }
        hashSet.add(current)
    }

    val result = hashSet.joinToString("")
    return if (result == "XMAS" || result == "SAMX") {
        1
    } else {
        0
    }
}

private fun traverseDown(matrix: List<List<Char>>, i: Int, j: Int): Int {
    val hashSet= mutableSetOf<Char>()
    hashSet.add(matrix[i][j])
    for (k in 1..3) {
        if (i + k > matrix.lastIndex) {
            return 0
        }
        val current = matrix[i + k][j]
        if (hashSet.contains(current)) {
            return 0
        }
        hashSet.add(current)
    }

    val result = hashSet.joinToString("")
    return if (result == "XMAS" || result == "SAMX") {
        1
    } else {
        0
    }
}

private fun traverseDiagnol(matrix: List<List<Char>>, i: Int, j: Int, word: String): Int {
    val hashSet= mutableSetOf<Char>()
    hashSet.add(matrix[i][j])
    for (k in 1..<word.length) {
        if (i + k > matrix.lastIndex || j + k > matrix[0].lastIndex) {
            return 0
        }
        val current = matrix[i + k][j + k]
        if (hashSet.contains(current)) {
            return 0
        }
        hashSet.add(current)
    }

    val result = hashSet.joinToString("")
    return if (result == word || result == word.reversed()) {
//        println("$i $j")
        1
    } else {
        0
    }
}

private fun traverseAltDiagnol(matrix: List<List<Char>>, i: Int, j: Int, word: String): Int {
    val hashSet= mutableSetOf<Char>()
    hashSet.add(matrix[i][j])
   for (k in 1..<word.length) {
        if (i + k > matrix.lastIndex || j - k < 0) {
            return 0
        }
        val current = matrix[i + k][j - k]
        if (hashSet.contains(current)) {
            return 0
        }
        hashSet.add(current)
    }

    val result = hashSet.joinToString("")
    return if (result == word || result == word.reversed()) {
//        println("$i $j")
        1
    } else {
        0
    }
}

fun main() {
    val input = readInput("Day04")
    val matrix = buildMatrix(input)

    val hashSet = mutableSetOf<Char>()

    var result = 0

    for (i in 0..matrix.lastIndex) {
        for (j in 0..matrix[0].lastIndex) {
            result += traverseRight(matrix, i, j) +
                      traverseDown(matrix, i ,j) +
                      traverseDiagnol(matrix, i, j, "XMAS") +
                      traverseAltDiagnol(matrix, i, j,"XMAS")
        }
    }

    println(result)

    result = 0

    for (i in 0..matrix.lastIndex) {
        for (j in 0..matrix[0].lastIndex - 2) {
            result += traverseDiagnol(matrix, i, j, "MAS") and
                    traverseAltDiagnol(matrix, i, j + 2,"MAS")
        }
    }

    println(result)
}