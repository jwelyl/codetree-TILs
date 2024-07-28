import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private val nums = Array(2) { IntArray(4) }
    private val rowSum = IntArray(2)
    private val colSum = IntArray(4)

    private var sum = 0

    fun solve() {
        for(i in 0 until 2) {
            st = StringTokenizer(br.readLine())

            for(j in 0 until 4) {
                nums[i][j] = st.nextToken().toInt()
                sum += nums[i][j]
                rowSum[i] += nums[i][j]
                colSum[j] += nums[i][j]
            }
        }
    
        for(i in 0 until 2)
            sb.append(String.format("%.1f", rowSum[i] / 4.0)).append("${if(i == 1) "\n" else " "}")
        for(i in 0 until 4)
            sb.append(String.format("%.1f", colSum[i] / 2.0)).append("${if(i == 3) "\n" else " "}")
        sb.append(String.format("%.1f\n", sum / 8.0))

        print(sb)
    }
}

fun main() {
    Main().solve()
}