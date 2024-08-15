import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var cnt1 = 0
    private var cnt2 = 0

    fun solve() {
        val input = br.readLine()
        var partial = input

        var idx = 0
        while(true) {
            partial = partial.substring(idx)

            val pos = partial.indexOf("ee")
            
            if(pos == -1)
                break

            cnt1++
            idx = pos + 1
        }

        partial = input
        idx = 0

        while(true) {
            partial = partial.substring(idx)
            val pos = partial.indexOf("eb")
            
            if(pos == -1)
                break

            cnt2++
            idx = pos + 1
        }

        println("$cnt1 $cnt2")
    }
}

fun main() {
    Main().solve()
}