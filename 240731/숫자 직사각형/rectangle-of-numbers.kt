import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var n = 0
    private var m = 0
    private var sum = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        var num = 1
        repeat(n) {
            repeat(m) {
                sb.append("${num++} ")
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}