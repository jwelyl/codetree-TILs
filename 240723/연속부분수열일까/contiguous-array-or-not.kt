import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n1 = 0
    private var n2 = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())

        n1 = tokens.nextToken().toInt()
        n2 = tokens.nextToken().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n1) {
            sb1.append(tokens.nextToken()).append("@")
        }

        tokens = StringTokenizer(br.readLine())
        repeat(n2) {
            sb2.append(tokens.nextToken()).append("@")
        }

        println(if(sb1.toString().indexOf(sb2.toString()) == -1) "No" else "Yes")
    }
}

fun main() {
    Main().solve()
}