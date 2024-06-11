import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var am = 0
var ae = 0
var bm = 0
var be = 0

fun main() {
    tokens = StringTokenizer(br.readLine())
    am = tokens.nextToken().toInt()
    ae = tokens.nextToken().toInt()
    tokens = StringTokenizer(br.readLine())
    bm = tokens.nextToken().toInt()
    be = tokens.nextToken().toInt()

    println(if(am > bm && ae > be) 1 else 0)
}