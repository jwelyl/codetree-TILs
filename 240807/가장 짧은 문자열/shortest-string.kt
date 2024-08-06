import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer

    private var maxLen = -1
    private var minLen = 21
    
    fun solve() {
        repeat(3) {
            val str = br.readLine()

            maxLen = maxOf(maxLen, str.length)
            minLen = minOf(minLen, str.length)
        }

        println(maxLen - minLen)
    }
}

fun main() {
    Main().solve()
}