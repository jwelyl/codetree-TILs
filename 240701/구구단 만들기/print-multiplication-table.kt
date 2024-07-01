import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        for(right in 1 .. 9) {
            for(left in b  downTo a step 2) {
                sb.append("$left * $right = ${left * right}")
                
                if(left != a)
                    sb.append(" / ")
                else
                    sb.append("\n")
            }
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}