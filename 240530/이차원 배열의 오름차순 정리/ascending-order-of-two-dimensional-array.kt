import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    
    private var n = 0L
    private var k = 0L

    fun solve() {
        n = br.readLine().toLong()
        k = br.readLine().toLong()

        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 1L
        var end = n * n
        var ret = n * n

        while(start <= end) {
            val mid = (start + end) / 2

            val cnt = cnts(mid) //  mid 이하 수의 개수

            if(cnt >= k) {  //  mid 이하 수의 개수가 k개 이상이면
                ret = mid   //  일단 mid 저장
                end = mid - 1   //  mid 더 작게 해보기, mid가 정확히 k번째 수가 될 때까지
            }
            else    //  mid 이하 수의 개수가 k보다 적을 경우
                start = mid + 1 //  mid를 키우기
        }

        return ret
    }

    //  num 이하 수의 개수
    private fun cnts(num : Long) : Long {
        var ret = 0L

        for(i in 1 .. n) {
            val q = num / i
            
            if(q == 0L)
                break
            
            ret += minOf(n, q)
        }

        return ret
    }
}

fun main() {
    Main().solve()
}