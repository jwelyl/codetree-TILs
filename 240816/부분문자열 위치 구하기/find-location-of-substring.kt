import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    fun solve() {
        val input = br.readLine()
        var target = br.readLine()

        println(input.indexOf(target))
    }
}

fun main() {
    Main().solve()
}