import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var sum = 0.0

    private var ok = false
    
    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        
        for(i in 0 until n)
            sum += tokens.nextToken().toDouble()

        println(String.format("%.1f", sum / n))

        if(sum >= 4.0)
            println("Perfect")
        else if(sum >= 3.0)
            println("Good")
        else
            println("Poor")
    }
}

fun main() {
    Main().solve()
}