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
            if(ch in 'A' .. 'Z')
                sb.append("${(ch.toInt() + diff).toChar()}")
            else if(ch in 'a' .. 'z' || ch in '0' .. '9')
                sb.append(ch)
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}