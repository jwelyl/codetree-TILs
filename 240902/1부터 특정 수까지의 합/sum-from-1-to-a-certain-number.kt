import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        println(n * (n + 1) / 20)
    }
}

fun main() {
    Main().solve()
}