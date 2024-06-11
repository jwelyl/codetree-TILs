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
    c = tokens.nextToken().toInt()

    println(if(b in a + 1 ..< c) 1 else 0)
}