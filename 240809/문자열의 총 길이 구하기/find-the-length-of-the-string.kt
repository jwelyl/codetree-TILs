import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var cnt = 0

    fun solve() {
        st = StringTokenizer(br.readLine())

        while(st.hasMoreTokens())
            cnt += st.nextToken().length

        println(cnt)
    }
}

fun main() {
    Main().solve()
}