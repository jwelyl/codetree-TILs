import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var sum = 0.0
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        for(i in 0 until 8)
            sum += tokens.nextToken().toDouble()

        println(String.format("%.1f", sum / 8))
    }
}

fun main() {
    Main().solve()
}