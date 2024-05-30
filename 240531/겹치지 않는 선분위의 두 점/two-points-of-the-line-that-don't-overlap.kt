import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  놓아야 할 점 개수
    private var m = 0   //  선분 개수

    private val lineSegments = ArrayList<LineSegment>() //  선분 리스트

    fun solve() {
        tokens = StringTokenizer(br.readLine())

        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        repeat(m) {
            tokens = StringTokenizer(br.readLine())

            val from = tokens.nextToken().toLong()
            val to = tokens.nextToken().toLong()

            lineSegments.add(LineSegment(from, to))
        }

        lineSegments.sort()

        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 0L
        var end = lineSegments[m - 1].to - lineSegments[0].from //  가장 앞의 선분의 시작점, 가장 뒤의 선분의 끝 점 사이 거리
        var ret = 0L    //  가장 가까운 두 점 사이의 거리의 최댓값

        while(start <= end) {
            val mid = (start + end) / 2 //  점 사이 최소 거리를 mid로 했을 때
            var cnt = 0L //  배치 가능한 점의 개수
            var ok = false

            var startPos = lineSegments[0].from //  첫 번째 선분의 가장 왼쪽 점에 점 하나 놓기
            var add = (lineSegments[0].to - startPos) / mid + 1 //  첫 번째 선분에 놓을 수 있는 점 개수
            var endPos = startPos + (add - 1) * mid //  첫 번째 선분에 놓인 가장 마지막 점 좌표

            cnt += add

            for(i in 1 .. m - 1) {  //  두 번째 선분부터 체크
                val line = lineSegments[i]  //  현재 선분

                startPos = endPos + mid //  다음 점 놓일 수 있는 시작 위치
                add = 0 //  현재 선분에 놓을 수 있는 점 개수

                if(startPos <= line.to) { //  시작 위치가 현재 선분의 가장 오른쪽 점보다 작거나 같아야 현재 선분에 하나라도 놓을 수 있음
                    if(startPos < line.from)    //  시작 위치가 현재 선분 왼쪽에 존재할 경우
                        startPos = line.from    //  현재 선분의 가장 왼쪽 점부터 놓기 시작
                    add = (line.to - startPos) / mid + 1
                    endPos = startPos + (add - 1) * mid
                }

                cnt += add

                if(cnt >= n) {  //  n개 이상의 점을 놓을 수 있을 경우
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