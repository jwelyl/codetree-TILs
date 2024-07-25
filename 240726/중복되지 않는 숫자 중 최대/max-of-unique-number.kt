import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private val cnts = IntArray(1_001)
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var ans = -1

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        
        repeat(n) {
            cnts[tokens.nextToken().toInt()]++
        }

        for(i in 1_000 downTo 1) {
            if(cnts[i] == 1) {
                ans = i
                break
            }
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}