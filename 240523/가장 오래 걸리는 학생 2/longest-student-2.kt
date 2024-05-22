import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  정점 개수
    private var m = 0   //  간선 개수

    private lateinit var dist : IntArray
    private lateinit var graph : Array<ArrayList<Node>>

    private var ans = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        dist = IntArray(n + 1) { Int.MAX_VALUE }
        graph = Array(n + 1 ) { ArrayList<Node>() }

        repeat(m) {
            tokens = StringTokenizer(br.readLine())

            val v1 = tokens.nextToken().toInt()
            val v2 = tokens.nextToken().toInt()
            val cost = tokens.nextToken().toInt()

            graph[v2].add(Node(v1, cost))
        }

        dijkstra()

        for(i in 1 .. n)
            ans = ans.coerceAtLeast(dist[i])

        println(ans)
    }

    private fun dijkstra() {
        val pq = PriorityQueue<Node>()
        dist[n] = 0
        pq.offer(Node(n, dist[n]))

        while(pq.isNotEmpty()) {
            val cur = pq.poll()
            val cv = cur.vertex
            val cc = cur.cost

            if(cc > dist[cv])
                continue

            for(next in graph[cv]) {
                val nv = next.vertex
                val nc = cc + next.cost

                if(nc < dist[nv]) {
                    dist[nv] = nc
                    pq.offer(Node(nv, dist[nv]))
                }
            }
        }
    }

    private class Node(val vertex : Int, val cost : Int) : Comparable<Node> {
        override fun compareTo(other : Node) : Int {
            return this.cost.compareTo(other.cost)
        }
    }
}

fun main() {
    Main().solve()
}