import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var m = 0

    private lateinit var nums : IntArray
    private var start = 1
    private var end = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        nums = IntArray(n)
        repeat(n) {
            nums[it] = br.readLine().toInt()

            end = end.coerceAtLeast(nums[it])
        }

        println(parametricSearch())
    }

    private fun parametricSearch() : Int {
        var ans = 1

        while(start <= end) {
            val mid = (start + end) / 2 //  나누는 정수 크기
            val cnt = divide(mid)       //  n개의 수를 mid로 나누었을 때 만들 수 있는 mid 크기의 정수 개수

            if(cnt < m) //  m개 이상 만들지 못할 경우
                end = mid - 1   //  나누는 정수 크기 줄여야 함
            else {  //  m개 이상 만들 수 있는 경우
                ans = mid           //  일단 mid 저장
                start = mid + 1     //  더 큰 수로 나눌 수 있나 체크
            }
        }

        return ans
    }

    private fun divide(divisor : Int) : Int {
        var ret = 0

        for(num in nums)
            ret += num / divisor

        return ret
    }
}

fun main() {
    Main().solve()
}