import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private val m1 = Array(3) { IntArray(3) }
    private val m2 = Array(3) { IntArray(3) }
    private val m = Array(3) { IntArray(3) }
    
    fun solve() {
        for(i in 0 until 3) {
            st = StringTokenizer(br.readLine())

            for(j in 0 until 3)
                m1[i][j] = st.nextToken().toInt()
        }

        br.readLine()

        for(i in 0 until 3) {
            st = StringTokenizer(br.readLine())

            for(j in 0 until 3)
                m2[i][j] = st.nextToken().toInt()
        }

        for(i in 0 until 3) {
            for(j in 0 until 3) {
                m[i][j] = m1[i][j] * m2[i][j]
                sb.append("${m[i][j]} ")
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}