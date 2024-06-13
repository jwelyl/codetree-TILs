import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var d = 0

fun main() {
   tokens = StringTokenizer(br.readLine())
   a = tokens.nextToken().toInt()
   b = tokens.nextToken().toInt()
   
   tokens = StringTokenizer(br.readLine())
   c = tokens.nextToken().toInt()
   d = tokens.nextToken().toInt()
   
   if(a == c)
        println(if(b > d) "A" else "B")
   else
        println(if(a > c) "A" else "B")

}