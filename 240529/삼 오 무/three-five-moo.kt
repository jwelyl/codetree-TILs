import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    private var n = 0L

    fun solve() {
        n = br.readLine().toLong()

        println(parametricSearch())
    }

    private fun parametricSearch() : Int {
        var start = 1
        var end = Int.MAX_VALUE

        var ret = Int.MAX_VALUE

        while(start <= end) {
            val mid = (start + end) / 2
            val cnt = cnt(mid)

           if(cnt >= n) {
               end = mid - 1
               ret = minOf(ret, mid)
           }
            else start = mid + 1
        }

        return ret
    }

    private fun cnt(num : Int) : Int {
        return num - num / 3 - num / 5 + num / 15
    }
}

fun main() {
    Main().solve()
}