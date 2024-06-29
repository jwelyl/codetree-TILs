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

        for(row in 0 .. a - 1) {
            if(row % 2 == 0) {
                val start = a * row + 1

                repeat(a) {
                    sb.append("${start + it} ")
                }
            }
            else {
                val start = (row + 1) * a

                repeat(a) {
                    sb.append("${start - it} ")
                }
            }

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}