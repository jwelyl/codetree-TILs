import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var minDiff = Int.MAX_VALUE
    private lateinit var nums : IntArray

    fun solve() {
        n = br.readLine().toInt()
        nums = IntArray(n)

        tokens = StringTokenizer(br.readLine())

        repeat(n) {
            nums[it] = tokens.nextToken().toInt()
        }

        for(i in 0 until n - 1)
            minDiff = minOf(minDiff, nums[i + 1] - nums[i])

        println(minDiff)
    }
}

fun main() {
    Main().solve()
}