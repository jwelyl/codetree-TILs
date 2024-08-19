import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private lateinit var str : CharArray

    fun solve() {
        str = br.readLine().toCharArray()
        for(i in 0 until str.size) {
            if(i != 1 && i != str.size - 2)
                sb.append(str[i])
        }

        println(sb)
    }
}

fun main() {
    Main().solve()
}