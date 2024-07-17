import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private val cnt = IntArray(10)

    fun solve() {
        n = br.readLine().toInt()
        tokens = StringTokenizer(br.readLine())

        repeat(n) {
            cnt[tokens.nextToken().toInt()]++
        }

        repeat(9) {
            sb.append("${cnt[it + 1]}\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}