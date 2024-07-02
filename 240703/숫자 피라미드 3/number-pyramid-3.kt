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
            for(col in 1 .. row)
                sb.append("${row * col} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}