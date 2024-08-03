import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private val arr = Array(16) { IntArray(16) { 1 } }
    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        for(r in 0 .. n - 1) {
            for(c in 0 .. r) {
                if(c == 0 || c == r)
                    arr[r][c] = 1
                else
                    arr[r][c] = arr[r - 1][c - 1] + arr[r - 1][c]
                
                sb.append("${arr[r][c]} ")
            }

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}