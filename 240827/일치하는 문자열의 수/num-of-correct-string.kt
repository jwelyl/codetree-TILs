import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private var n = 0
    private var str = ""
    private var ans = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        str = st.nextToken()

        repeat(n) {
            if(str == br.readLine())
                ans++
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}