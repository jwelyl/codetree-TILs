import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var max = -1

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        repeat(10) {
            val num = tokens.nextToken().toInt()

            if(num > max)
                max = num
        }

        println(max)
    }
}

fun main() {
    Main().solve()
}