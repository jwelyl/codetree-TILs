import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    while(a != 1) {
        if(a % 2 == 0)
            a /= 2
        else
            a = a * 3 + 1

        b++
    }

    println(b)
}