import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private var n = 0
    private var sum = 0

    fun solve() {
        n = br.readLine()

        repeat(n) {
            sum += br.readLine().toInt()
        }

        println(sum.toString().substring(1) + sum.toString()[0])
    }
}

fun main() {
    Main().solve()
}