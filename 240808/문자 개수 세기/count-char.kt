import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer
    
    fun solve() {
        val str = br.readLine()
        val ch = br.readLine()[0]
        var ans = 0

        for(c in str) {
            if(c == ch)
                ans++
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}