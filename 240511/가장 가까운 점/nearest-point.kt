import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0  //  점 개수
    private var m = 0  //  작업 횟수

    private var pq = PriorityQueue<Point>()

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        repeat(n) {
            tokens = StringTokenizer(br.readLine())
            pq.offer(Point(tokens.nextToken().toInt(), tokens.nextToken().toInt()))
        }

        repeat(m) {
            val closest = pq.poll()
            pq.offer(Point(closest.x + 2, closest.y + 2))
        }

        println("${pq.peek().x} ${pq.peek().y}")
    }

    private class Point(val x : Int, val y : Int) : Comparable<Point> {
        override fun compareTo(other: Point): Int {
            var ret = (abs(this.x) + abs(this.y)).compareTo(abs(other.x) + abs(other.y))

            if(ret == 0)
                ret = this.x.compareTo(other.x)
            if(ret == 0)
                ret = this.y.compareTo(other.y)

            return ret
        }
    }
}

fun main() {
    Main().solve()
}