import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer
    
    private var n = 0
    private var m = 0
    
    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for(i in minOf(n, m) downTo 1) {
            if(n % i == 0 && m % i == 0) {
                println(i)
                break
            }
        }
    }
}

fun main() {
    Main().solve()
}