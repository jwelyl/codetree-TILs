import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0

fun main() {
   tokens = StringTokenizer(br.readLine())
    
   a = tokens.nextToken().toInt()
   b = tokens.nextToken().toInt()
   // c = tokens.nextToken().toInt(
    // a = br.readLine().toInt

    var ans = 0

    if(a >= 90) {
      if(b >= 95) ans = 10
      else if(b >= 90) ans = 5
    }

    println(ans * 10_000)

    // println(if(a % 13 == 0 || a % 19 == 0) "True" else "False")
}