import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0  //  수 개수
    private var m = 0  //  제거 횟수

    private var pq = PriorityQueue<Int>(reverseOrder())

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            pq.offer(tokens.nextToken().toInt())
        }

        repeat(m) {
            val max = pq.poll()
            pq.offer(max - 1)
        }

        print(pq.peek())
    }
}

fun main() {
    Main().solve()
}