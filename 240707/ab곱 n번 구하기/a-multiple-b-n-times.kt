import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0

    private var ch = 'A'

    fun solve() {
        // tokens = StringTokenizer(br.readLine())
        // a = tokens.nextToken().toInt()
        // b = tokens.nextToken().toInt()
        a = br.readLine().toInt()

        repeat(a) {
            tokens = StringTokenizer(br.readLine())
            val from = tokens.nextToken().toInt()
            val to = tokens.nextToken().toInt()
            var mult = 1

            for(i in from .. to)
                mult *= i

            sb.append("$mult\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}