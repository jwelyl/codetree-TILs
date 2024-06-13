import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var d = 0

fun main() {
    repeat(3) {
        tokens = StringTokenizer(br.readLine())
        val x = tokens.nextToken()[0]
        val y = tokens.nextToken().toInt()

        if(x == 'Y') {
            if(y >= 37)
                a++
            else
                c++
        }
        else {
            if(y >= 37)
                b++
            else
                d++
        }

    }

    println(if(a >= 2) 'E' else 'N')
}