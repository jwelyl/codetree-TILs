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
            val num = tokens.nextToken().toInt()

            if(it == 2 || it == 4 || it == 9)
                sum += num
        }

        println(sum)
    }
}

fun main() {
    Main().solve()
}