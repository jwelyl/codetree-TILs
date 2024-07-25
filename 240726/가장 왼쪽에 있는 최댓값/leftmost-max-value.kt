import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private lateinit var nums : IntArray

    private var max = -1             //  현재 최댓값
    private val idxList = ArrayList<Int>()

    fun solve() {
        n = br.readLine().toInt()
        nums = IntArray(n)

        tokens = StringTokenizer(br.readLine())

        repeat(n) {
            nums[it] = tokens.nextToken().toInt()
        }

        for(i in 0 until n) {
            if(nums[i] > max) {
                idxList.add(i + 1)
                max = nums[i]
            }
        }

        for(i in idxList.size - 1 downTo 0)
            sb.append("${idxList[i]} ")

        print(sb)
    }
}

fun main() {
    Main().solve()
}