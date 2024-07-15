import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            val num = tokens.nextToken().toInt()

            sb.append("${num * num} ")
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}