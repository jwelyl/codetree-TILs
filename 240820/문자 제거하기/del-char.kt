import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""

    fun solve() {
        str = br.readLine()

        val cnt = str.length

        repeat(cnt - 1) {
            var idx = br.readLine().toInt()

            if(idx >= str.length)
                idx = str.length - 1;

            str = str.substring(0, idx) + str.substring(idx + 1)

            sb.append("$str\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}