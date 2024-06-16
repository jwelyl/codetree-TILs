import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer
val sb = StringBuilder()

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
    // if(a > b && a > c)
    //     println(if(b > c) b else c)
    // else if(b > a && b > c)
    //     println(if(a > c) a else c)
    // else
    //     println(if(a > b) a else b)

    // for(i in b downTo a)
    //     print("$i ")

    // var num = 26
    // while(num >= 10)
    //     print("${num--} ")
    
    sb.append(a / b).append(".")
    a %= b

    repeat(20) {
        a *= 10
        sb.append(a / b)
        a %= b
    }

    println(sb)
}