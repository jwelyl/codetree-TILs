import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0

fun main() {
    a = br.readLine().toInt()

    println(if(a in 10 .. 20) "yes" else "no")
}