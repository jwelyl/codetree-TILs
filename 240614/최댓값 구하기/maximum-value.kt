import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var max = -101

fun main() {
   tokens = StringTokenizer(br.readLine())
   max = maxOf(max, tokens.nextToken().toInt())
   max = maxOf(max, tokens.nextToken().toInt())
   max = maxOf(max, tokens.nextToken().toInt())
    
   println(max)
}