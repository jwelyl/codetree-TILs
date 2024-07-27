import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer
 
    fun solve() {
        repeat(4) {
            var sum = 0
            tokens = StringTokenizer(br.readLine())
            repeat(4) {
                sum += tokens.nextToken().toInt()
            }

            sb.append("$sum\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}