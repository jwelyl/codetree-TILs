import java.util.*
import java.io.*

const val diff = 'A'.toInt() - 'a'.toInt()

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    fun solve() {
        repeat(5) {
            tokens = StringTokenizer(br.readLine())

            repeat(3) {
                var ch = tokens.nextToken()[0]
                ch = (ch.toInt() + diff).toChar()
                sb.append("$ch ")
            }

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}