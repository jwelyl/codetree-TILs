import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    while(true) {
        a = br.readLine().toInt()

        if(a == 0) {
            print(sb)
            break
        }

        sb.append("$a\n")
    }
}