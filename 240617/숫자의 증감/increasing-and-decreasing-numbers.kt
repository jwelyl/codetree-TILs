import java.util.*
import java.io.*

val br = BufferedReader(InputStreamReader(System.`in`))
lateinit var tokens : StringTokenizer

var a = '0'
var b = 0
var c = 0
var d = 0

fun main() {
    tokens = StringTokenizer(br.readLine())
    a = tokens.nextToken()[0]
    b = tokens.nextToken().toInt()
//     c = tokens.nextToken().toInt()
    
    a = br.readLine().toInt()
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
    // repeat(a) {
    //     println("LeebrosCode")
    // }

    if(a == 'A') {
        for(i in 1 .. b)
            println("$i ")
    }
    else {
        for(i in n downTo 1)
            println("$i ")
    }
}