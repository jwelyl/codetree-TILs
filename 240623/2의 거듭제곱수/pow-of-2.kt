import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    var num = 2
    var ans = 1

    while(num < a) {
        ans++
        num *= 2
    }

    println(ans)
}