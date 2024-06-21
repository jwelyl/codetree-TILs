import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    var mult = 1

    for(i in 1 .. 10) {
        mult *= i
        if(mult >= a) {
            println(i)
            break
        }
    }
}