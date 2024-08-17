import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private lateinit var str : CharArray

    fun solve() {
        str = br.readLine().toCharArray()
        val first = str[0]
        val second = str[1]

        for(i in 0 until str.size) {
            if(str[i] == second)
                str[i] = first
        }

        println(String(str))
    }
}

fun main() {
    Main().solve()
}