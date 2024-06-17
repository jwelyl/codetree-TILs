import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = 0
var b = 0
var c = 0
var d = 0

fun main() {
    // tokens = StringTokenizer(br.readLine())
    // a = tokens.nextToken().toInt()
    // b = tokens.nextToken().toInt()
//     c = tokens.nextToken().toInt()
    
    a = br.readLine().toInt()

    for(i in a .. 100) {
        var output = '0'

        if(i >= 90)
            output = 'A'
        else if(i >= 80)
            output = 'B'
        else if(i > = 70)
            output = 'C'
        else if(i > = 60)
            output = 'D'
        else
            output = 'F'
        print("$output ")
    }
}