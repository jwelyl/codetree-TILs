import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  정수 개수
    private lateinit var nums : IntArray        //  주어진 정수 배열

    private val pq = PriorityQueue<Int>()       //  최솟값 찾는 pq

    private var sum = 0.0
    private var ans = Double.MIN_VALUE

    fun solve() {
        n = br.readLine().toInt()

        nums = IntArray(n + 1)

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            nums[it + 1] = tokens.nextToken().toInt()
        }
    
        sum += nums[n]
        pq.offer(nums[n])
        for(k in n - 2 downTo 1) {  //  앞에서부터 k개 제거
            sum += nums[k + 1]      //  앞에서부터 k개 지우고 남은 수들 합
            pq.offer(nums[k + 1])   //  pq에 남은 수들 저장

            var avg = (sum - pq.peek()) / (n - k - 1)

            if(ans < avg)
                ans = avg
        }

        println(String.format("%.2f", ans))
    }
}

fun main() {
    Main().solve()
}