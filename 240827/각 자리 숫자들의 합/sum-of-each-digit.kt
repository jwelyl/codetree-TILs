import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private var str = ""
    private var ans = 0

    fun solve() {
        str = br.readLine()

        for(ch in str)
            ans += (ch.toInt() - '0'.toInt())

        println(ans)
    }
}

fun main() {
    Main().solve()
}