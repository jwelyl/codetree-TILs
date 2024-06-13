import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0

fun main() {
//    tokens = StringTokenizer(br.readLine())
//    a = tokens.nextToken().toInt()
//    b = tokens.nextToken().toInt()
a = br.readLine().toInt()

   println(if(a % 4 == 0) !(a % 100 == 0 && a % 400 != 0) else false)
}