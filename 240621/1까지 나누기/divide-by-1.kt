import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    var cnt = 0
    var d = 1
    while(a >= 2) {
        cnt++
        a /= d
        d++
    }

    println(cnt)
}