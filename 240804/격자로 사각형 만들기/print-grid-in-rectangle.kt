import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private val arr = Array(10) { IntArray(10) {1} }
    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        for(r in 1 until n) {
            for(c in 1 until n)
                arr[r][c] = arr[r - 1][c - 1] + arr[r - 1][c] + arr[r][c - 1]
        }

        for(r in 0 until n) {
            for(c in 0 until n)
                sb.append("${arr[r][c]} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}