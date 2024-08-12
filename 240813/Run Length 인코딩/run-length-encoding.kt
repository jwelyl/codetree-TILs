import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var input = ""

    fun solve() {
        input = br.readLine()

        var before = ' '
        var cnt = 0

        for(ch in input) {
            if(before == ch)
                cnt++
            else {
                if(before == ' ') {
                    before = ch
                    cnt++
                }
                else {
                    sb.append(before).append(cnt)
                    before = ch
                    cnt = 1
                }
            }
        }

        sb.append(before).append(cnt)

        println(sb.length)
        println(sb)
    }
}

fun main() {
    Main().solve()
}