import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0
var c = '0'

fun main() {
    var sum = 0.0
    var cnt = 0

    while(true) {
       a = br.readLine().toInt()

       if(a in 20 .. 29) {
            sum += 20
            cnt+=
       }
       else
            break
        
        println("${String.format("%.2f", sum / cnt)}")
    }

    print(sb)
}