import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var sum = 0

    fun solve() {
        for(i in 0 until 4) {
            st = StringTokenizer(br.readLine())
            
            for(j in 0 util 4) {
                val num = st.nextToken().toInt()

                if(j <= i)
                    sum += num
            }
        }

        println(sum)
    }
}

fun main() {
    Main().solve()
}