import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()

        a = br.readLine().toInt()

        for(row in 1 .. a) {
            val num = 2 * row + 7
            for(col in 1 .. a)
                sb.append("${num + 2 * col} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}