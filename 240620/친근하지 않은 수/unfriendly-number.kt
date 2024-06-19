import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

var a = 0
var b = 0

fun main() {
    a = br.readLine().toInt()

    var cnt = 0

    for(i in 1 .. a) {
        if(i % 2 != 0 && i % 3 != 0 && i % 5 != 0)
            cnt++
    }
    
    print("$cnt")
}