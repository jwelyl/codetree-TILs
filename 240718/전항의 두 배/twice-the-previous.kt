import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var f1 = 1
    private var f2 = 0
    private var f = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        f1 = tokens.nextToken().toInt()
        f2 = tokens.nextToken().toInt()

        sb.append("$f1 $f2 ")
        repeat(8) {
            f = f2 + 2 * f1
            f1 = f2
            f2 = f

            sb.append("$f ")
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}