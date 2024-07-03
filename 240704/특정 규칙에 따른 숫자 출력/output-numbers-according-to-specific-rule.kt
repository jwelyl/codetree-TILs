import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    private var num = 1

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()
        a = br.readLine().toInt()

        for(row in a downTo 1) {
            repeat(a - row) {
                sb.append("  ")
            }

            for(col in row downTo 1)
                sb.append("${num++} ")

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}