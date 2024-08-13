import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var n = 0

    fun solve() {
        val tmp = StringBuilder()

        n = br.readLine().toInt()

        st = StringTokenizer(br.readLine())
        repeat(n) {
            tmp.append(st.nextToken())
        }

        val input = tmp.toString()

        val q = input.length / 5
        val r = input.length % 5

        var idx = 0
        repeat(q) {
            repeat(5) {
                sb.append(input[idx++])
            }
            sb.append("\n")
        }

        repeat(r) {
            sb.append(input[idx++])
        }
        sb.append("\n")

        print(sb)
    }
}

fun main() {
    Main().solve()
}