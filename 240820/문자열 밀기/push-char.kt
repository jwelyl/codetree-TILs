import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""

    fun solve() {
        str = br.readLine()

        str = str.substring(1) + str[0]

        println(str)
    }
}

fun main() {
    Main().solve()
}