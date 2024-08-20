import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""
    private var len = 0

    fun solve() {
        str = br.readLine()
        len = str.length

        sb.append("$str\n")
        repeat(len) {
            str = str[len - 1] + str.substring(0, len - 1)
            sb.append("$str\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}