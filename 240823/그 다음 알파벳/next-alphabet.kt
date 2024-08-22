import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var a = ' '

    fun solve() {
        a = br.readLine()[0]
        
        println(if(a in 'a' .. 'y') (a.toInt() + 1).toChar() else 'a')
    }
}

fun main() {
    Main().solve()
}