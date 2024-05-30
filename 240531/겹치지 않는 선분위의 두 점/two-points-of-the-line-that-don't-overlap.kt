import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  놓아야 할 점 개수
    private var m = 0   //  선분 개수

    private val lineSegments = ArrayList<LineSegment>() //  선분 리스트

    private var minPos = Long.MAX_VALUE     //  선분 위의 점 중 가장 작은 점
    private var maxPos = Long.MIN_VALUE     //  선분 위의 점 중 가장 큰 점

    fun solve() {
        tokens = StringTokenizer(br.readLine())

        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        repeat(m) {
            tokens = StringTokenizer(br.readLine())

            val from = tokens.nextToken().toLong()
            val to = tokens.nextToken().toLong()

            lineSegments.add(LineSegment(from, to))

            minPos = minPos.coerceAtMost(from)
            maxPos = maxPos.coerceAtLeast(to)
        }


        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 0L
        var end = maxPos - minPos //  가장 앞의 선분의 시작점, 가장 뒤의 선분의 끝 점 사이 거리
        var ret = 0L    //  가장 가까운 두 점 사이의 거리의 최댓값

        while(start <= end) {
            val mid = (start + end) / 2 //  점 사이 최소 거리를 mid로 했을 때
            var cnt = 0L //  배치 가능한 점의 개수
            var ok = false

            for(lineSegment in lineSegments) {
                cnt += (1 + (lineSegment.to - lineSegment.from) / mid)    //  점 사이 간격 mid로 했을 때 해당 선분에 놓을 수 있는 점 개수

                if(cnt >= n) {
                    ok = true
                    break
                }
            }

            if(ok) {    //  mid 간격으로 n개 점을 놓을 수 있을 경우
                ret = mid
                start = mid + 1     //  간격 더 넓혀보기
            }
            else    //  mid 간격으로 n개 점을 놓을 수 없는 경우
                end = mid - 1   //  간격 더 좁혀보기
        }

        return ret
    }

    private class LineSegment(val from : Long, val to : Long) : Comparable<LineSegment> {
        override fun compareTo(other : LineSegment) : Int { //  선분 시작점 기준 오름차순
            return this.from.compareTo(other.from)
        }
    }
}

fun main() {
    Main().solve()
}