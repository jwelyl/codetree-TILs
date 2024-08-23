import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val diff = 'a'.toInt() - 'A'.toInt()

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""

    fun solve() {
        str = br.readLine()
        
        for(ch in str) {
            if(ch in 'a' .. 'z')
                sb.append("${(ch.toInt() - diff).toChar()}")
            else
                sb.append(ch)
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}