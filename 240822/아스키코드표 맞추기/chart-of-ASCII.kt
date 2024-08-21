import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    fun solve() {
        st = StringTokenizer(br.readLine())
        
        repeat(5) {
            val num = st.nextToken().toInt()

            sb.append("${num.toChar()} ")
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}