import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0
    private val pq = PriorityQueue<Int>(reverseOrder())

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            val x = br.readLine().toInt()

            if(x > 0)
                pq.offer(x)
            else
                sb.append("${if(pq.isNotEmpty()) pq.poll() else 0}\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}