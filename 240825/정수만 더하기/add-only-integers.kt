import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""
    private var sum = 0

    fun solve() {
        str = br.readLine()
        
        for(ch in str) {
            if(ch in '0' .. '9')
                sum += ch.toInt() - '0'.toInt()
        }

        println(sum)
    }
}

fun main() {
    Main().solve()
}