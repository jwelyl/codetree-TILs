import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var ch1 = ' '
    private var ch2 = ' '

    fun solve() {
        st = StringTokenizer(br.readLine())
        ch1 = st.nextToken()[0]
        ch2 = st.nextToken()[0]

        println("${ch1.code + ch2.code} ${abs(ch1.code - ch2.code)}")
    }
}

fun main() {
    Main().solve()
}