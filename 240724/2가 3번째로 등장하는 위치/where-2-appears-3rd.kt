import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var cnt = 0

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        for(i in 0 until n) {
            val num = tokens.nextToken().toInt()

            if(num == 2) {
                cnt++

                if(cnt == 3) {
                    println(i + 1)
                    return
                }
            }
        }
    }
}

fun main() {
    Main().solve()
}