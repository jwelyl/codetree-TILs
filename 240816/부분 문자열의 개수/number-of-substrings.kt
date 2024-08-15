import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var cnt1 = 0

    fun solve() {
        val input = br.readLine()
        var partial = input

        val target = br.readLine()

        var idx = 0
        while(true) {
            partial = partial.substring(idx)

            val pos = partial.indexOf(target)
            
            if(pos == -1)
                break

            cnt1++
            idx = pos + 1
        }

        println(cnt1)
    }
}

fun main() {
    Main().solve()
}