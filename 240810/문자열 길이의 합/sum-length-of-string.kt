import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var n = 0
    private var sum = 0
    private var cntA = 0

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            val str = br.readLine()

            if(str[0] == 'a')
                cntA++
            sum += str.length
        }

        println("$sum $cntA")
    }
}

fun main() {
    Main().solve()
}