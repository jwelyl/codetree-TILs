import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0.0
var b = 0.0

fun main() {
    a = br.readLine().toDouble()
    b = br.readLine().toDouble()

    println(if(a >= 1.0 && b >= 1.0) "High" else { if(a >= 0.5 && b >= 0.5) "Middle" else "Low"})
}