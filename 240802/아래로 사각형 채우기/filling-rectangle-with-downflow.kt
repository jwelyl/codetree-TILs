import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private val mat = Array(10) { IntArray(10) }

    private var n = 0
    
    fun solve() {
       n = br.readLine().toInt()

        repeat(n) {
            var num = it + 1

            repeat(n) {
                sb.append("$num ")
                num += n
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}