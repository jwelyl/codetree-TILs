import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb1 = StringBuilder()
    private val sb2 = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var first = Int.MIN_VALUE
    private var second = Int.MIN_VALUE

    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        
        repeat(n) {
            val num = tokens.nextToken().toInt()

            if(num >= first) {  //  기존 최댓값보다 크거나 같은 값이 들어온 경우
                second = first  //  기존 최댓값이 두 번째로 큰 값
                first = num     //  새로 들어온 값이 최댓값
            }
            else if(num > second)   //  최댓값보다는 작고 두 번째로 큰 값보단 큰 경우
                second = num        //  두 번째로 큰 값 갱신
        }

        println("$first $second")
    }
}

fun main() {
    Main().solve()
}