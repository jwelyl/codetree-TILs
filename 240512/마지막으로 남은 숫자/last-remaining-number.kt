import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private val pq = PriorityQueue<Int>(reverseOrder())

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            pq.offer(tokens.nextToken().toInt())
        }

        while(pq.size >= 2) {
            val n1 = pq.poll()
            val n2 = pq.poll()

            if(n1 != n2)
                pq.offer(n1 - n2)
        }

        println(if(pq.isNotEmpty()) pq.poll() else -1)
    }
}

fun main() {
    Main().solve()
}