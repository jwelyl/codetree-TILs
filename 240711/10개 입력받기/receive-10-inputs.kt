import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var sum = 0.0
    private var cnt = 0
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        for(i in 0 until tokens.countTokens()) {
            val num = tokens.nextToken().toInt()

            if(num != 0) {
                sum += num;
                cnt++;
            }   
            else
                break;
        }

        println(String.format("%d %.1f", sum.toInt(), sum / cnt))
    }
}

fun main() {
    Main().solve()
}