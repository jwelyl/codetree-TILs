import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var tc = 0
    private var pass = 0
    
    fun solve() {
        tc = br.readLine().toInt()

        repeat(tc) {
            var sum = 0
            var res = ""
            tokens = StringTokenizer(br.readLine())

            repeat(4) {
                sum += tokens.nextToken().toInt()
            }

            if(sum >= 240) {
                pass++
                res = "pass\n"
            } else res = "fail\n"

            sb.append(res)
        }

        sb.append(pass)

        println(sb)
    }
}

fun main() {
    Main().solve()
}