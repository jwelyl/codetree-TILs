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
            val cost = tokens.nextToken().toLong()

            graph[v1].add(Node(v2, cost))
            graph[v2].add(Node(v1, cost))
        }

        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        dijkstra()  //  b부터 각 지점까지 최단거리 구하기

        for(i in 1 .. n)
            graph[i].sortBy { it.v }

        var curV = a
        var curDist = 0L //  curV까지 누적 거리
        
        sb.append("${dist[a]}\n")

        while(curV != b) {
            sb.append("$curV ")

            for(next in graph[curV]) {
                val nextV = next.v  //  curV와 연결된 정점

                if(curDist + next.c + dist[nextV] == dist[a]) {   //  curV와 nextV 사이의 간선 길이 + nextV와 b 사이의 거리 = a와 b 사이의 거리이면
                    curV = nextV    //  다음 정점은 nextV
                    curDist += next.c
                    break
                }
            }
        }

        sb.append(curV)

        println(sb)
    }

    private fun dijkstra() {
        val pq = PriorityQueue<Node>()

        dist[b] = 0
        pq.offer(Node(b, dist[b]))

        while(pq.isNotEmpty()) {
            val cur = pq.poll()
            val cv = cur.v
            val cc = cur.c

            if(dist[cv] < cc)
                continue

            for(next in graph[cv]) {
                val nv = next.v
                val nc = cur.c + next.c

                if(nc < dist[nv]) { //  cv를 거쳐서 nv까지 가는게 더 빠를 경우
                    dist[nv] = nc
                    pq.offer(Node(nv, dist[nv]))
                }
            }
        }
    }

    private class Node(val v : Int, val c : Long) : Comparable<Node> {
        override fun compareTo(other : Node) : Int {
            return this.c.compareTo(other.c)
        }
    }
}

fun main() {
    Main().solve()
}