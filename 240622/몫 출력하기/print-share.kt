import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    var cnt = 0

    while(cnt < 3) {
        a = br.readLine().toInt()

        if(a % 2 == 0) {
            cnt++
            sb.append("${a / 2}\n")
        }
    }

    print(sb)
}