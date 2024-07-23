import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var min = Int.MAX_VALUE
    private var cnt = 0

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            val num = tokens.nextToken().toInt()

            if(min > num) {
                min = num
                cnt = 1
            }
            else if(min == num)
                cnt++
        }

        println("$min $cnt")
    }
}

fun main() {
    Main().solve()
}