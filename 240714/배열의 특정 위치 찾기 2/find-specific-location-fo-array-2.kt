import java.util.*
import java.io.*
import kotlin.math.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var sum1 = 0
    private var sum2 = 0
    
    fun solve() {
        tokens = StringTokenizer(br.readLine()) 

        repeat(10) {
            val num = tokens.nextToken().toInt()
            
            if(it % 2 == 0)
                sum1 += num
            else
                sum2 += num
        }

        println(abs(sum1 - sum2))
    }

}

fun main() {
    Main().solve()
}