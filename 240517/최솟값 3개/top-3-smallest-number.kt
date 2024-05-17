import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer
    private val sb = StringBuilder()

    private var n = 0
    private val pq = PriorityQueue<Int>()

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            pq.offer(tokens.nextToken().toInt())

            if(pq.size < 3)
                sb.append("-1\n")
            else {
                val n1 = pq.poll().toLong()
                val n2 = pq.poll()
                val n3 = pq.poll()

                sb.append("${n1 * n2 * n3}\n")

                pq.offer(n1.toInt())
                pq.offer(n2)
                pq.offer(n3)
            }
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}