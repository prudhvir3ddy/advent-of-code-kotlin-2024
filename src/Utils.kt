import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

/**
 * Binary search with first and last index customisation
 */
fun List<Int>.binarySearch(target: Int, searchType: BinarySearchType): Int {
    var start = 0
    var end = this.lastIndex

    var result = -1

    while (start <= end) {
        val mid = start + (end - start) / 2

        when {
            target == this[mid] -> {
                result = mid
                when(searchType) {
                    BinarySearchType.FIRST -> {
                        end = mid - 1
                    }

                    BinarySearchType.LAST -> {
                        start = mid + 1
                    }
                }
            }

            this[mid] > target -> {
                end = mid - 1
            }

            else -> {
                start = mid + 1
            }
        }
    }

    return result
}
