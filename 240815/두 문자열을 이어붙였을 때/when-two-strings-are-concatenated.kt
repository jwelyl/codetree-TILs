import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    fun solve() {
        val a = br.readLine()
        val b = br.readLine()

        println(a + b == b + a)
    }
}

fun main() {
    Main().solve()
}