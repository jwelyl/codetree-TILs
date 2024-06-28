import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    private var cur = 2

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()

        a = br.readLine().toInt()

        for(row in 1 .. a) {
            for(col in 1 .. a) {
                sb.append("$cur ")
                cur = next()
            }

            sb.append("\n")
        }

        print(sb)
    }

    private fun next() : Int {
        return if(cur + 2 == 10) 2 else cur + 2
    }
}

fun main() {
    Main().solve()
}