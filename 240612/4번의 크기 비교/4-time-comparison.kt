import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var d = 0
var e = 0

fun main() {
    a = br.readLine().toInt()

    tokens = StringTokenizer(br.readLine())

    b = tokens.nextToken().toInt()
    c = tokens.nextToken().toInt()
    d = tokens.nextToken().toInt()
    e = tokens.nextToken().toInt()

    println(if(a > b) 1 else 0)
    println(if(a > c) 1 else 0)
    println(if(a > d) 1 else 0)
    println(if(a > e) 1 else 0)
}