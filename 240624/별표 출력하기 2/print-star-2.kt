import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0
var c = 0

fun main() {
    // tokens = StringTokenizer(br.readLine())
    // a = tokens.nextToken().toInt()
    // b = tokens.nextToken().toInt()
    // c = tokens.nextToken().toInt()

    a = br.readLine().toInt()
    
    for(i in a downTo 1) {
        repeat(i) {
            sb.append("* ")
        }
    }
    
    sb.append("\n")

    print(sb)
}