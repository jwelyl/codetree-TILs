import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var m = 0L
    private var a = 0
    private var b = 0

    private var min = 0L        //  최소 찾는 횟수
    private var max = 0L     //  최대 찾는 횟수

    fun solve() {
        m = br.readLine().toLong()

        min = m + 1

        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()

        for(target in a .. b) {
            val cnt = binarySearch(target)

            min = min.coerceAtMost(cnt.toLong())
            max = max.coerceAtLeast(cnt.toLong())
        }

        println("$min $max")
    }

    //  정석적인 이진탐색으로 target을 찾기 위한 탐색 횟수
    private fun binarySearch(target : Int) : Int {
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