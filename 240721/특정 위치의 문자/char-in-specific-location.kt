import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = '0'

    fun solve() {
        a = br.readLine()[0]

        println(when(a) {
            'L' -> 0
            'E' -> 1
            'B' -> 2
            'R' -> 3
            'O' -> 4
            'S' -> 5
            else -> "None"
        })
    }
}

fun main() {
    Main().solve()
}