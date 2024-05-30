import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  좌표 수
    private var m = 0   //  놓아야 하는 물건 수

    private lateinit var pos : IntArray     //  좌표 수 

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        pos = IntArray(n)
 
        repeat(n) {
            pos[it] = br.readLine().toInt()
        }

        pos.sort()  //  좌표 오름차순 정렬

        println(parametricSearch())
    }

    private fun parametricSearch() : Int {
        var start = 1
        var end = pos[n - 1] - pos[0]
        var ret = 0

        while(start <= end) {
            val mid = (start + end) / 2

            if(canPut(mid)) {       //  최소 간격을 mid로 했을 때 m개의 물건을 놓을 수 있을 경우
                ret = mid           //  일단 mid 저장
                start = mid + 1     //  간격 더 키워보기
            }
            else    //  현재 간격으로는 m개의 물건을 놓을 수 없을 경우
                end = mid - 1       //  간격 더 줄여보기
        }

        return ret
    }

    //  0번째 좌표에 물건 한 개 놓고 최소 dist 간격으로 놓았을 때 나머지 m - 1개를 놓을 수 있으면 true
    private fun canPut(dist : Int) : Boolean {
        var remain = m - 1      //  더 놓아야 하는 물건 개수
        var prevIdx = 0         //  이전에 물건 놓은 좌표 index

        for(i in 1 .. n - 1) {
            if(pos[i] - pos[prevIdx] >= dist) {  //  이전에 물건 놓은 좌표와 현재 좌표 간격이 dist 이상이면
                remain--    //  현재 좌표에 놓을 수 있음
                prevIdx = i
            
                if(remain == 0) //  모든 물건을 다 놓을 수 있으면
                    break
            }
        }

        return remain == 0
    }
}

fun main() {
    Main().solve()
}