import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var m = 0

    private lateinit var graph : Array<ArrayList<Node>>
    private lateinit var dist : LongArray
    private lateinit var prev : IntArray

    private var a = 0
    private var b = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        graph = Array(n + 1) { ArrayList<Node>() }
        dist = LongArray(n + 1) { Long.MAX_VALUE }
        prev = IntArray(n + 1)

        repeat(m) {
            tokens = StringTokenizer(br.readLine())
            val v1 = tokens.nextToken().toInt()
            val v2 = tokens.nextToken().toInt()
            val cost = tokens.nextToken().toLong()

            graph[v1].add(Node(v2, cost))
            graph[v2].add(Node(v1, cost))
        }

        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        dijkstra()
        path()
    }

    private fun dijkstra() {
        val pq = PriorityQueue<Node>()

        dist[a] = 0
        pq.offer(Node(a, dist[a]))

        while(pq.isNotEmpty()) {
            val cur = pq.poll()
            val cv = cur.v
            val cc = cur.c

            if(dist[cv] < cc)
                continue

            for(next in graph[cv]) {
                val nv = next.v
                val nc = cur.c + next.c

                if(nc < dist[nv]) {
                    dist[nv] = nc
                    pq.offer(Node(nv, dist[nv]))
                    prev[nv] = cv
                }
                else if(nc == dist[nv]) {
                    if(cv < prev[cv]) {
                        pq.offer(Node(nv, dist[nv]))
                        prev[nv] = cv
                    }
                }
            }
        }
    }

    private fun path() {
        val stack = Stack<Int>()

        var cur = b

        while(cur != 0) {
            stack.add(cur)
            cur = prev[cur]
        }

        sb.append("${dist[b]}\n")

        while(stack.isNotEmpty())
            sb.append("${stack.pop()} ")

        println(sb)
    }

    private class Node(val v : Int, val c : Long) : Comparable<Node> {
        override fun compareTo(other : Node) : Int {
            var ret = this.c.compareTo(other.c)

            if(ret == 0)
                ret = this.v.compareTo(other.v)

            return ret
        }
    }
}

fun main() {
    Main().solve()
}