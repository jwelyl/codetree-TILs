import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private val mat = Array(10) { IntArray(10) }

    private var n = 0
    private var m = 0
    
    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for(i in 0 until n) {
            st = StringTokenizer(br.readLine())

            for(j in 0 until m)
                mat[i][j] = st.nextToken().toInt()
        }

        for(i in 0 until n) {
            st = StringTokenizer(br.readLine())

            for(j in 0 until m) {
                val num = st.nextToken().toInt()

                sb.append(if(mat[i][j] == num) "0 " else "1 ")
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}