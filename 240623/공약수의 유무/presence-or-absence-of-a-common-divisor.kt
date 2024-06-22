import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0
var c = 0

fun main() {
    // a = br.readLine().toInt()
    tokens = StringTokenizer(br.readLine())
    a = tokens.nextToken().toInt()
    b = tokens.nextToken().toInt()
    // c = tokens.nextToken().toInt()

    for(i in a .. b) {
        if(1920 % i == 0 && 2880 % i == 0) {
            println(1)
            return
        }
    }

    println(0)
}