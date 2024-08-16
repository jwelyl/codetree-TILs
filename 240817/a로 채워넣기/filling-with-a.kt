import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private lateinit var str : CharArray

    fun solve() {
        str = br.readLine().toCharArray()

        str[1] = 'a'
        str[str.length - 2] = 'a'

        println(String(str))
    }
}

fun main() {
    Main().solve()
}