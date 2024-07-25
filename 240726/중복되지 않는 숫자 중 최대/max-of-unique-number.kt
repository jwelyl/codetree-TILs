import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private val cnts = IntArray(1_001)

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        
        repeat(n) {
            cnts[tokens.nextToken().toInt()]++
        }

        for(i in 1_000 downTo 1) {
            if(cnts[i] == 1) {
                println(i)
                break
            }
        }
    }
}

fun main() {
    Main().solve()
}