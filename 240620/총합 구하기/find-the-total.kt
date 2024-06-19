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

    var sum = 0

    for(i in a .. b) {
        if(i % 6 == 0 || i % 8 != 0)
            sum += i
    }

    print("$sum")
}