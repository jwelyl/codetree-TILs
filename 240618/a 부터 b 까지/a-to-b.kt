import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var d = 0

fun main() {
    tokens = StringTokenizer(br.readLine())
    a = tokens.nextToken().toInt()
    b = tokens.nextToken().toInt()
//     c = tokens.nextToken().toInt()
    
    // a = br.readLine().toInt()

    for(i in a .. b) {
        var output = i

        if(i % 2 == 0)
            output *= 2
        else
            output += 3
        print("$output ")
    }
}