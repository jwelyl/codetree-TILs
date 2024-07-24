import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var max = -999
    private var min = 999

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        while(tokens.hasMoreTokens()) {
            val num = tokens.nextToken().toInt()

            if(num == 999 || num == -999) {
                println("$max $min")
                break
            }

            max = maxOf(max, num)
            min = minOf(min, num)
        }
    }
}

fun main() {
    Main().solve()
}