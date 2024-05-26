import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var m = 0

    private lateinit var nums : IntArray
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())

        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        nums = IntArray(n + 1)

        tokens = StringTokenizer(br.readLine())
        for(i in 1 .. n)
            nums[i] = tokens.nextToken().toInt()

        tokens = StringTokenizer(br.readLine())
        for(i in 1 .. m)
            sb.append("${binarySearch(tokens.nextToken().toInt())}\n")

        print(sb)
    }

    private fun binarySearch(target : Int) : Int {
        var pos = -1

        var start = 1
        var end = n

        while(start <= end) {
            val mid = (start + end) / 2

            if(target == nums[mid]) {
                pos = mid
                end = mid - 1
            }
            else if(target < nums[mid])
                end = mid - 1
            else
                start = mid + 1
        }

        return pos
    }
}

fun main() {
    Main().solve()
}