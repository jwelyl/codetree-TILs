import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private lateinit var nums : IntArray

    private var max = 0

    fun solve() {
        n = br.readLine().toInt()
        nums = IntArray(n)

        tokens = StringTokenizer(br.readLine())

        repeat(n) {
            nums[it] = tokens.nextToken().toInt()
        }

        for(i in 0 until n - 1) {
            for(j in i + 1 until n) {
                if(max < nums[j] - nums[i])
                    max = nums[j] - nums[i]
            }
        }

        println(max)
    }
}

fun main() {
    Main().solve()
}