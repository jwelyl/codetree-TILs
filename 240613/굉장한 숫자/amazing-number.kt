import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0

fun main() {
   // tokens = StringTokenizer(br.readLine())
    
   // a = tokens.nextToken().toInt()
   // b = tokens.nextToken().toInt()
   // c = tokens.nextToken().toInt(
    a = br.readLine().toInt()

    println(if((a % 3 == 0 && a % 2 == 1) || a % 10 == 0) "true" else "false")
}