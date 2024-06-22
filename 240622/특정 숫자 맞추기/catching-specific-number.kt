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

        sb.append(if(a < 25) "Higher\n" else { if(a > 25) "Lower\n" else "Good\n" })
        if(a == 25)
            break
    }

    print(sb)
}