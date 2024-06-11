import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var min = 0

fun main() {
    tokens = StringTokenizer(br.readLine())

    a = tokens.nextToken().toInt()
    b = tokens.nextToken().toInt()
    c = tokens.nextToken().toInt()

    min = minOf(a, b, c)

    print("${if(a == min) 1 else 0} ")
    print(if(a == b && b == c) 1 else 0)
}