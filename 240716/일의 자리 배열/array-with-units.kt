import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var f0 = 0
    private var f1 = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        f0 = tokens.nextToken().toInt()
        f1 = tokens.nextToken().toInt()

        sb.append("$f0 $f1 ")

        repeat(8) {
            val tmp = (f0 + f1) % 10
            f0 = f1
            f1 = tmp
            sb.append("$f1 ")
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}