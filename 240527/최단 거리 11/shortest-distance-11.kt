import java.util.*
import kotlin.math.min

fun main() {
    val sc = Scanner(System.`in`)
    val sb = StringBuilder()

    val n = sc.nextInt()
    val m = sc.nextInt()

    val graph = List(n) { MutableList(n) { 0 } }
    repeat(m) {
        val start = sc.nextInt() - 1
        val end = sc.nextInt() - 1
        val dist = sc.nextInt()

        graph[start][end] = dist
        graph[end][start] = dist
    }

    val a = sc.nextInt() - 1
    val b = sc.nextInt() - 1

    val dist = MutableList(n) { 1e9.toInt() }
    val visited = MutableList(n) { false }

    dist[b] = 0

    for(i in 0 until n) {
        var minIndex = -1

        for(j in 0 until n) {
            if(visited[j]) continue

            if(minIndex == -1 || dist[minIndex] > dist[j]) {
                minIndex = j
            }
        }

        visited[minIndex] = true

        for(j in 0 until n) {
            if(graph[minIndex][j] == 0) continue

            dist[j] = min(dist[j], dist[minIndex] + graph[minIndex][j])
        }
    }

    sb.append("${dist[a]}\n")
    var x = a
    sb.append("${x+1} ")
    while(x != b) {
        for (i in 0 until n) {
            if(graph[i][x] == 0) continue

            if (dist[i] + graph[i][x] == dist[x]) {
                x = i
                break
            }
        }
        sb.append("${x+1} ")
    }

    println(sb.toString())
}