import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer
    private val sb = StringBuilder()

    private var n = 0  //  수 개수
    private var m = 0  //  질의 개수

    private var treeSet = TreeSet<Int>()

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            treeSet.add(tokens.nextToken().toInt())
        }

        tokens = StringTokenizer(br.readLine())
        repeat(m) {
            val floor = treeSet.floor(tokens.nextToken().toInt())

            if(floor == null)
                sb.append("-1\n")
            else {
                sb.append("$floor\n")
                treeSet.remove(floor)
            }
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}