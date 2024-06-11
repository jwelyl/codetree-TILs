import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var min = 101

fun main() {
    tokens = StringTokenizer(br.readLine())
    
    a = tokens.nextToken().toInt()
    b = tokens.nextToken().toInt()
    c = tokens.nextToken().toInt()

    if(a < min)
        min = a
    if(b < min)
        min = b
    if(c < min)
        min = c

    println(min)
}