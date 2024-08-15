import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    
    fun solve() {
        st = StringTokenizer(br.readLine())
        val input1 = st.nextToken()
        val input2 = st.nextToken()
        val idx = input1.indexOf(input2)
        print(if(idx == -1) "No " else idx)
    }
}

fun main() {
    Main().solve()
}