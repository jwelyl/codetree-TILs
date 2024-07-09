import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var sum = 0
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        repeat(10) {
            sum += tokens.nextToken().toInt()
        }

        println(String.format("%d %.1f\n", sum, sum / 10.0))
    }
}

fun main() {
    Main().solve()
}