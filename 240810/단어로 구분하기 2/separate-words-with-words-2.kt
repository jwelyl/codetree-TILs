import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    fun solve() {
        st = StringTokenizer(br.readLine())

        var order = 1
        while(st.hasMoreTokens()) {
            val str = st.nextToken()

            if(order % 2 == 1)
                sb.append(str).append("\n")
            order++
        }
        
        print(sb)
    }
}

fun main() {
    Main().solve()
}