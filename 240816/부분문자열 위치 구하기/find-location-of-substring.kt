import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    fun solve() {
        println(br.readLine().indexOf(br.readLine()))
    }
}

fun main() {
    Main().solve()
}