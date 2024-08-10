import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer
    private val stack = Stack<String>()

    fun solve() {
        st = StringTokenizer(br.readLine())

        while(st.hasMoreTokens())
            stack.push(st.nextToken())
        
        while(stack.isNotEmpty())
            sb.append(stack.pop()).append("\n")

        print(sb)
    }
}

fun main() {
    Main().solve()
}