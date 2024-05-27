import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var m = 0L
    private var a = 0L
    private var b = 0L

    private var min = 0L        //  최소 찾는 횟수
    private var max = 0L     //  최대 찾는 횟수

    fun solve() {
        m = br.readLine().toLong()

        min = m + 1

        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toLong()
        b = tokens.nextToken().toLong()

        for(target in a .. b) {
            val cnt = binarySearch(target)

            min = min.coerceAtMost(cnt.toLong())
            max = max.coerceAtLeast(cnt.toLong())
        }

        println("$min $max")
    }

    //  정석적인 이진탐색으로 target을 찾기 위한 탐색 횟수
    private fun binarySearch(target : Long) : Int {
        var start = 1L
        var end = m
        var cnt = 1

        while(start <= end) {
            val mid = (start + end) / 2

            if(mid == target.toLong())
                break
            else if(mid < target)
                start = mid + 1
            else
                end = mid - 1

            cnt++
        }

        return cnt
    }
}

fun main() {
    Main().solve()
}