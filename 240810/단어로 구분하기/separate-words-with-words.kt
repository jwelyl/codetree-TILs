import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    fun solve() {
        st = StringTokenizer(br.readLine())

        while(st.hasMoreTokens())
            sb.append(st.nextToken()).append("\n")
        
        print(sb)
    }
}

fun main() {
    Main().solve()
}