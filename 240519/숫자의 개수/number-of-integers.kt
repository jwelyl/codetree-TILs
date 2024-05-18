import java.util.*
import java.io.*
import java.lang.StringBuilder
import kotlin.collections.ArrayList

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens: StringTokenizer
    private val sb = StringBuilder()

    private var n = 0   //  자연수 개수
    private var m = 0   //  질의 개수

    private val nums = ArrayList<Int>()

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        m = tokens.nextToken().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            nums.add(tokens.nextToken().toInt())
        }

        repeat(m) {
            val target = br.readLine().toInt()

            val lower = lowerBound(target)  //  nums에서 target이 가장 처음에 나타나는 위치
            val upper = upperBound(target)  //  nums에서 target이 가장 마지막에 나타나는 위치

            sb.append("${if(lower == -1 || upper == -1) 0 else upper - lower + 1}\n")
        }

        print(sb)
    }

    private fun lowerBound(target : Int) : Int {
        var ret = -1    //  존재하지 않으면 -1

        var start = 0
        var end = n - 1

        while(start <= end) {
            val mid = (start + end) / 2

            if(nums[mid] == target) {   //  찾은 경우
                ret = mid
                end = mid - 1;  //  더 왼쪽에서 찾아보기
            }
            else if(nums[mid] < target) //  더 작은 경우
                start = mid + 1 //  더 오른쪽에서 찾아보기
            else    //  더 큰 경우
                end = mid - 1   //  더 왼쪽에서 찾아보기

        }

        return ret
    }

    private fun upperBound(target : Int) : Int {
        var ret = -1    //  존재하지 않으면 -1

        var start = 0
        var end = n - 1

        while(start <= end) {
            val mid = (start + end) / 2

            if(nums[mid] == target) {   //  찾은 경우
                ret = mid
                start = mid + 1;  //  더 오른쪽에서 찾아보기
            }
            else if(nums[mid] < target) //  더 작은 경우
                start = mid + 1 //  더 오른쪽에서 찾아보기
            else    //  더 큰 경우
                end = mid - 1   //  더 왼쪽에서 찾아보기

        }

        return ret
    }

}

fun main() {
    Main().solve()
}