import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    private var s = 0L

    fun solve() {
        s = br.readLine().toLong()

        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 1L
        var end = 2000000000L

        var ans = 0L

        while(start <= end) {
            val mid = (start + end) / 2

            if(mid * (mid + 1) / 2 <= s) {  //  1부터 mid까지의 합이 s 이하일 경우
                ans = mid           //  mid는 가능한 n 값임
                start = mid + 1     //  mid 더 키워보기
            }
            else end = mid - 1      //  mid 더 줄여야 함
        }

        return ans
    }
}

fun main() {
    Main().solve()
}