import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    var sum = 0

    for(i in 1 .. Int.MAX_VALUE) {
        sum += i
        if(sum == a) {
            println(i)
            break
        }
    }
}