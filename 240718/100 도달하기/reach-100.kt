import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var f1 = 1
    private var f2 = 0
    private var f = 0

    fun solve() {
        n = br.readLine().toInt()
        f2 = n

        sb.append("$f1 $f2 ")
        while(true) {
            f = f1 + f2
            f1 = f2
            f2 = f

            sb.append("$f ")

            if(f > 100)
                break
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}