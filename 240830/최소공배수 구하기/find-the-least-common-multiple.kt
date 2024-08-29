import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer

    private var n = 0
    private var m = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for(i in 1 .. n * m) {
            if(i % n == 0 && i % m == 0) {
                println(i)
                break
            }
        }
    }
}

fun main() {
    Main().solve()
}