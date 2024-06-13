import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0

val days = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun main() {
//    tokens = StringTokenizer(br.readLine())
//    a = tokens.nextToken().toInt()
//    b = tokens.nextToken().toInt()
a = br.readLine().toInt()

   println(days[a])
}