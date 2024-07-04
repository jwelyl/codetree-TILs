import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    private var num = 0

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()
        a = br.readLine().toInt()

        for(left in 1 .. a) {
            for(right in 1 .. a) {
                sb.append("($left, $right) ")
                if((left + right) % 4 == 0)
                    sb.append("\n")
            }
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}