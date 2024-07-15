import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var before = -1

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        for(i in 0 until 10) {
            val num = tokens.nextToken().toInt()

            if(num % 3 == 0) {
                println(before)
                break
            }

            before = num
        }
    }
}

fun main() {
    Main().solve()
}