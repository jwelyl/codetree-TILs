import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0
var c = '0'

fun main() {
    while(true) {
        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()
        c = tokens.nextToken()[0]

        sb.append(a * b).append("\n")
        if(c == 'C')
            break
    }

    print(sb)
}