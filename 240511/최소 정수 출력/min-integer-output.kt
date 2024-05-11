import java.util.*
import java.io.*
import java.lang.StringBuilder

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0  //  수 개수

    private var pq = PriorityQueue<Int>()

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            val num = br.readLine().toInt()

            if(num == 0)
                sb.append("${if(pq.isEmpty()) 0 else pq.poll()}\n")
            else
                pq.offer(num)
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}