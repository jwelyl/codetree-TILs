import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer
    
    fun solve() {
        repeat(2) {
            st = StringTokenizer(br.readLine())
            while(st.hasMoreTokens())
                sb.append(st.nextToken())
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}