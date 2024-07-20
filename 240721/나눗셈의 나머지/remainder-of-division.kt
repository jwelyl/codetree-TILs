import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0
    private val cnts = IntArray(10)
    private var sum = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        while(a > 1) {
            cnts[a % b]++

            a /= b
        }

        for(i in 0 until 10)
            sum += cnts[i] * cnts[i]

        println(sum)
    }
}

fun main() {
    Main().solve()
}