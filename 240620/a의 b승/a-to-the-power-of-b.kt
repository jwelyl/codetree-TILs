import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    tokens = StringTokenizer(br.readLine())
    a = tokens.nextToken().toInt()
    b = tokens.nextToken().toInt()

    var mult = 1

    repeat(b) {
        mult *= a
    }

    print("$mult")
}