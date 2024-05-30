import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  물건 개수
    private var m = 0   //  통로 개수

    private lateinit var times : IntArray   //  각 통로별 통과에 필요한 시간
    private var maxTime = 0                 //  가장 오래 걸리는 통로의 통과 시간

    fun solve() {
        tokens = StringTokenizer(br.readLine())

        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        times = IntArray(m)

        repeat(m) {
            times[it] = br.readLine().toInt()
        }

        times.sortDescending()
        maxTime = times[m - 1]

        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 0L
        var end = maxTime.toLong() * n  //  모든 물건이 가장 오래 걸리는 통로로 통과한다 가정했을 때 시간
        var ret = 0L

        while(start <= end) {
            val mid = (start + end) / 2 //  mid 시간 동안 n개의 물건이 통과 가능한지 확인
        
            var cnt = 0L //  mid 시간 동안 통과 가능한 물건 수

            for(i in 0 until m) {
                cnt += mid / times[i]   //  mid 시간 동안 i 통로로 통과 가능한 물건 개수
                if(cnt >= n.toLong())   //  모두 통과 가능할 경우
                    break
            }

            if(cnt >= n.toLong()) {   //  mid 동안 모두 통과 가능할 경우
                ret = mid             //  일단 mid 저장
                end = mid - 1         //  더 짧은 시간 동안 통과 가능한지 확인
            }
            else    //  mid 동안 모두 통과 불가능할 경우
                start = mid + 1       //  더 긴 시간 동안 통과 가능한지 확인
        }

        return ret
    }
}

fun main() {
    Main().solve()
}