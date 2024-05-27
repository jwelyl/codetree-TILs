import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer
    
    private var n = 0   //  점 개수
    private var m = 0   //  선분 개수

    private lateinit var pos : IntArray //  좌표 배열

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        pos = IntArray(n + 2)

        tokens = StringTokenizer(br.readLine())
        for(i in 1 .. n)
            pos[i] = tokens.nextToken().toInt()
        pos[n + 1] = Int.MAX_VALUE

        pos.sort()

        repeat(m) {
            tokens = StringTokenizer(br.readLine())
            val left = tokens.nextToken().toInt()
            val right = tokens.nextToken().toInt()

            val lower = lowerPos(left) + 1
            val upper = upperPos(right) - 1

            if(lower > upper)
                sb.append("0\n")
            else
                sb.append("${upper - lower + 1}\n")
        }

        print(sb)
    }

    //  target보다 작은 좌표 중 최대인 좌표 찾기
    private fun lowerPos(target : Int) : Int {
        var start = 0
        var end = n + 1
        var ret = 0

        while(start <= end) {
            val mid = (start + end) / 2

            if(pos[mid] < target) { //  target보다 mid의 좌표가 작을 경우
                ret = mid           //  일단 mid 저장
                start = mid + 1     //  더 오른쪽에서 찾아보기
            }
            else
                end = mid - 1   //  더 왼쪽에서 찾아보기
        }

        return ret
    }

    //  target보다 큰 좌표 중 최소인 좌표 찾기
    private fun upperPos(target : Int) : Int {
        var start = 0
        var end = n + 1
        var ret = n + 1

        while(start <= end) {
            val mid = (start + end) / 2

            if(pos[mid] > target) { //  target보다 mid의 좌표가 클 경우
                ret = mid           //  일단 mid 저장
                end = mid - 1     //  더 왼쪽에서 찾아보기
            }
            else
                start = mid + 1   //  더 오른쪽에서 찾아보기
        }

        return ret
    }
}

fun main() {
    Main().solve()
}