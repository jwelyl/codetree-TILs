import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer

    private var a = 0
    private var b = 0
    private var c = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        a = st.nextToken().toInt()
        b = st.nextToken().toInt()
        c = st.nextToken().toInt()

        println(minOf(a, b, c))
    }
}

fun main() {
    Main().solve()
}