import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var cnt = 0

    fun solve() {
        while(true) {
            val input = br.readLine()

            if(input == "0") {
                println(cnt)
                print(sb)
                break
            }

            cnt++
            if(cnt % 2 == 1)
                sb.append(input).append("\n")
        }
    }
}

fun main() {
    Main().solve()
}