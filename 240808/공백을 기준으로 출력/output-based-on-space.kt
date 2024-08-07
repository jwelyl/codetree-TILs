import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer
    
    fun solve() {
        repeat(2) {
            st = StringTokenizer(br.readLine())
            sb.append(st.nextToken()).append(st.nextToken()).append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}