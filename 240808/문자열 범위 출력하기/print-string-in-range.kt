import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer
    
    fun solve() {
        println(br.readLine().substring(2, 10))
    }
}

fun main() {
    Main().solve()
}