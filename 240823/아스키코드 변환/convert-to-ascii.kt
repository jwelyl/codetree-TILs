import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var a = ' '
    private var b = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        
        a = st.nextToken()[0]
        b = st.nextToken().toInt()

        println("${a.toInt()} ${b.toChar()}")
    }
}

fun main() {
    Main().solve()
}