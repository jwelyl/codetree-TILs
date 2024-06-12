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

    var season = ""

    if(a == 12 || a in 1 .. 2)
     season = "Winter"
    else if(a in 3 .. 5)
     season = "Spring"
    else if(a in 6 .. 8)
     season = "Summer"
    else season = "Fall"


    println(season)
}