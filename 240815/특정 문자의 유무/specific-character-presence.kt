import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    fun solve() {
        val input = br.readLine()
        print(if(input.indexOf("ee") == -1) "No " else "Yes ")
        println(if(input.indexOf("ab") == -1) "No" else "Yes")
    }
}

fun main() {
    Main().solve()
}