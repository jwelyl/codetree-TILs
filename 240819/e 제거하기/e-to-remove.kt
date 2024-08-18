import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""
    private var find = false

    fun solve() {
        str = br.readLine()

        for(ch in str) {
            if(ch == 'e' && !find)
                find = true
            else
                sb.append(ch)
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}