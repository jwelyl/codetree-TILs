import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var sum = 0.0
    private var cnt = 0

    private var ok = false
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        repeat(10) {
            val num = tokens.nextToken().toInt()

            sum += num
            
            if(num >= 250)
                ok = true

            if(it == 9 && ok) {
                cnt = 9
                sum -= num
            } else cnt = 10
        }

        println(String.format("%d %.1f\n", sum.toInt(), sum / cnt))
    }
}

fun main() {
    Main().solve()
}