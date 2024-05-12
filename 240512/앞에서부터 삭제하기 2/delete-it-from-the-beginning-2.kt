import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0   //  정수 개수
    private lateinit var nums : IntArray        //  주어진 정수 배열
    private lateinit var prefix : IntArray      //  왼쪽부터 누적합
    private lateinit var postfix : IntArray     //  오른쪽부터 최솟값

    private var ans = Double.MIN_VALUE

    fun solve() {
        n = br.readLine().toInt()

        nums = IntArray(n + 1)
        prefix = IntArray(n + 1)
        postfix = IntArray(n + 1)

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            nums[it + 1] = tokens.nextToken().toInt()
            prefix[it + 1] = prefix[it] + nums[it + 1]
        }

        postfix[n] = nums[n]
        for(i in n - 1 downTo 1) {
            if(nums[i] < postfix[i + 1])
                postfix[i] = nums[i]
            else
                postfix[i] = postfix[i + 1]
        }

        for(k in 1 .. n - 2) {
            val avg = (0.0 + prefix[n] - prefix[k] - postfix[k + 1]) / (n - k - 1)

            if(avg > ans)
                ans = avg
        }

        println(String.format("%.2f", ans))
    }
}

fun main() {
    Main().solve()
}