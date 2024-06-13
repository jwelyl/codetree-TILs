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
b = br.readLine().toInt()

   println(if(a == 1) {
    if(b >= 19)
        "MAN"
    else
        "BOY"
   } else {
    if(b >= 19)
        "WOMAN"
    else
        "GIRL"
   })
}