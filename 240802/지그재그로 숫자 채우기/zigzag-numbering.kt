import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private lateinit var mat : Array<IntArray>

    private var n = 0
    private var m = 0
    private var num = 0
    
    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        mat = Array(n) { IntArray(m) }

        for(col in 0 until m) {
            if(col % 2 == 0) {
                for(row in 0 until n)
                    mat[row][col] = num++
            }
            else {
                for(row in n - 1 downTo 0)
                    mat[row][col] = num++
            }
        }

        for(row in 0 until n) {
            for(col in 0 until m)
                sb.append("${mat[row][col]} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}