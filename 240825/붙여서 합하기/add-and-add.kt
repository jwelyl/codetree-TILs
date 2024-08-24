import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val diff = 'a'.toInt() - 'A'.toInt()

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private var strA = ""
    private var strB = ""

    fun solve() {
        st = StringTokenizer(br.readLine())

        strA = st.nextToken()
        strB = st.nextToken()

        println((strA + strB).toInt() + (strB + strA).toInt())
    }
}

fun main() {
    Main().solve()
}