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

        for(ch in input)
            sb.append(ch).append("\n")

        print(sb)
    }
}

fun main() {
    Main().solve()
}