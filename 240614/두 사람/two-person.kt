import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var asex = '0'
var b = 0
var bsex = '0'

fun main() {
   tokens = StringTokenizer(br.readLine())
   a = tokens.nextToken().toInt()
   asex = tokens.nextToken()[0]
   
   tokens = StringTokenizer(br.readLine())
   b = tokens.nextToken().toInt()
   bsex = tokens.nextToken()[0]
   
   println(if((a >= 19 && asex == 'M') || (b >= 19 && bsex == 'M')) 1 else 0)
}