import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private val num1 = 0
    private val num2 = 0

    private var ans = 0
    fun solve() {
        st = StringTokenizer(br.readLine())

        num1 = st.nextToken().toInt()
        num2 = st.nextToken().toInt()
        
        for(ch in (num1 + num2).toString()) {
            if(ch == '1')
                ans++
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}