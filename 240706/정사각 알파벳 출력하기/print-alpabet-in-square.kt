import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    private var ch = 'A'

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()
        a = br.readLine().toInt()

        for(i in 1 .. a) {
            for(j in 1 .. a) {
                sb.append(ch)
                ch = (ch.code + 1).toChar()
            }

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}