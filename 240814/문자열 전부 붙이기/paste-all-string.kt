import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            sb.append(br.readLine())
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}