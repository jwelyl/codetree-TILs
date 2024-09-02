import java.util.*
import java.io.*
import kotlin.collections.ArrayList

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer
    
    private var a = 0
    private var b = 0
    private var ans = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        a = st.nextToken().toInt()
        b = st.nextToken().toInt()

        for(i in a .. b) {
            if(i % 3 == 0) {
                ans++
                continue
            }

            val str = i.toString()

            for(ch in str) {
                if(ch == '3' || ch == '6' || ch == '9') {
                    ans++
                    break
                }
            }
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}