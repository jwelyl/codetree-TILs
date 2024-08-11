import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var n = 0
    private val list = ArrayList<String>()
    private var key = ' '
    private var cnt = 0
    private var sum = 0.0

    fun solve() {
        val input = br.readLine()
        n = br.readLine().toInt()

        for(i in input.length - 1 downTo maxOf(input.length - n, 0))
            sb.append(input[i])

        println(sb)
    }
}

fun main() {
    Main().solve()
}