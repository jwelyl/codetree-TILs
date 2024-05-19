import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var m = 0

    private lateinit var graph : Array<ArrayList<Node>>
    private lateinit var dist : LongArray
    private var a = 0
    private var b = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        graph = Array(n + 1) { ArrayList<Node>() }
        dist = LongArray(n + 1) { Long.MAX_VALUE }

        repeat(m) {
            tokens = StringTokenizer(br.readLine())

            val v1 = tokens.nextToken().toInt()
            val v2 = tokens.nextToken().toInt()
            val c = tokens.nextToken().toLong()

            graph[v1].add(Node(v2, c))
            graph[v2].add(Node(v1, c))
        }

        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        dijkstra()

        println(dist[b])
    }

    private fun dijkstra() {
        val pq = PriorityQueue<Node>()
        dist[a] = 0
        pq.offer(Node(a, 0))

        while(pq.isNotEmpty()) {
            val cNode = pq.poll()
            val cVertex = cNode.vertex
            val cCost = cNode.cost

            if(cCost > dist[cVertex])   //  cVertex의 기존에 알려진 최단거리보다 클 경우 가치가 없음
                continue

            for(nNode in graph[cVertex]) {
                val nVertex = nNode.vertex
                val nCost = cCost + nNode.cost  //  nVertex까지 cVertex를 거쳐서 갈 경우의 거리

                if(dist[nVertex] > nCost) { //  cVertex를 거쳐서 가는게 더 거리가 짧을 경우
                    dist[nVertex] = nCost   //  nVertex까지의 최단거리 갱신
                    pq.offer(Node(nVertex, dist[nVertex]))
                }
            }
        }
    }   //  dijkstra-end

    private class Node(val vertex : Int, val cost : Long) : Comparable<Node> {
        override fun compareTo(other : Node) : Int {
            return this.cost.compareTo(other.cost)
        }
    }
}

fun main() {
    Main().solve()
}