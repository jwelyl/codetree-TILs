import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private val visited = Array(9) { BooleanArray(9) }
    private var n = 0
    private var m = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()
    
        repeat(m) {
            st = StringTokenizer(br.readLine())
            val y = st.nextToken().toInt() - 1
            val x = st.nextToken().toInt() - 1

            visited[y][x] = true
        }

        for(r in 0 until n) {
            for(c in 0 until n)
                sb.append("${if(visited[r][c]) 1 else 0} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}