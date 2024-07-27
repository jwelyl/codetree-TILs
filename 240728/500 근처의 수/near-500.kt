import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var max = Int.MIN_VALUE
    private var min = Int.MAX_VALUE
 
    fun solve() {
        tokens = StringTokenizer(br.readLine())

        repeat(10) {
            val num = tokens.nextToken().toInt()

            if(num < 500 && num > max)
                max = num
            if(num > 500 && num < min)
                min = num
        }

        println("$max $min")
    }
}

fun main() {
    Main().solve()
}