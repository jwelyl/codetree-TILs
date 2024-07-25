import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private lateinit var nums : IntArray

    private var beforeMax = 1_001   //  이전 최댓값
    private var beforeMaxIdx = 0    //  이전 최댓값 위치
    private var max = 0             //  현재 최댓값
    private var maxIdx = 0          //  현재 최댓값 위치

    fun solve() {
        n = br.readLine().toInt()

        beforeMaxIdx = n
        nums = IntArray(n)

        tokens = StringTokenizer(br.readLine())

        repeat(n) {
            nums[it] = tokens.nextToken().toInt()
        }

        while(beforeMaxIdx > 0) {
            max = -1

            for(i in 0 until beforeMaxIdx) {
                if(nums[i] < beforeMax && nums[i] > max) {
                    max = nums[i]
                    maxIdx = i
                }
            }

            sb.append("${maxIdx + 1} ")
            beforeMax = max
            beforeMaxIdx = maxIdx
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}