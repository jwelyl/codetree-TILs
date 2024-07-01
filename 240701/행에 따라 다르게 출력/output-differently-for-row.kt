import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0
    private var cur = 0

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()

        a = br.readLine().toInt()

        for(row in 1 .. a) {
            val add = if(row % 2 == 1) 1 else 2

            for(col in 1 .. a) {
                cur += add
                sb.append("$cur ")
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}