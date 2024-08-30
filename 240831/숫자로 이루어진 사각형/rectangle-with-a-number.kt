import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer
    
    private var n = 0
    private var num = 1
    
    fun solve() {
        n = br.readLine()

        repeat(n) {
            repeat(n) {
                sb.append("$num ")
                num = if(num + 1 == 10) 1 else num + 1
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}