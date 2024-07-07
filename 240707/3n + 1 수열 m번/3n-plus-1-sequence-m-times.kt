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

        repeat(a) {
            var num = br.readLine().toInt()
            var cnt = 0

            while(num != 1) {
                if(num % 2 == 0)
                    num /= 2
                else
                    num = num * 3 + 1
                cnt++
            }

            sb.append("$cnt\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}