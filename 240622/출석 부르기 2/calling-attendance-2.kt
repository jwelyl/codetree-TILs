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

        if(a == 1)
            sb.append("John\n")
        else if(a == 2)
            sb.append("Tom\n")
        else if(a == 3)
            sb.append("Paul\n")
        else if(a == 4)
            sb.append("Sam\n")
        else {
            sb.append("Vacancy\n")
            break
        }
    }

    print(sb)
}