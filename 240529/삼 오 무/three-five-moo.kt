import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))

    private var n = 0L

    fun solve() {
        n = br.readLine().toLong()

        println(parametricSearch())
    }

    private fun parametricSearch() : Long {
        var start = 1L
        var end = (Int.MAX_VALUE).toLong()
        var ret = 0L

        while(start <= end) {
            val mid = (start + end) / 2

            if(cnt(mid) == n) {
                if(mid % 3 == 0L && mid % 5 == 0L) {
                    for(num in mid - 1 downTo 1) {
                        if(num % 3 != 0L && num % 5 != 0L) {
                            ret = num
                            break
                        }
                    }
                }
                else ret = mid

                break
            }
            else if(cnt(mid) < n)
                start = mid + 1
            else
                end = mid - 1
        }

        return ret
    }

    //  1부터 num까지 수 중 3의 배수 또는 5의 배수인 수를 제외한 수의 개수
    private fun cnt(num : Long) : Long {
        val cnt3 = num / 3          //  1부터 num까지 수 중 3의 배수 개수
        val cnt5 = num / 5          //  1부터 num까지 수 중 5의 배수 개수
        val cnt15 = num / 15        //  1부터 num까지 수 중 15의 배수

        return num - cnt3 - cnt5 + cnt15
    }
}

fun main() {
    Main().solve()
}