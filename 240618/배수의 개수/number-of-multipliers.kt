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
    // tokens = StringTokenizer(br.readLine())
    // a = tokens.nextToken().toInt()
    // b = tokens.nextToken().toInt()
//     c = tokens.nextToken().toInt()
    
    // a = br.readLine().toInt()

    // var i = a
    // while(i <= b) {
    //     print("$i ")

    //     if(i % 2 == 1)
    //         i *= 2
    //     else
    //         i += 3
    // }

    // for(i in 1 .. a) {
    //     val str = i.toString()

    //     if(str.indexOf("3") != -1 || str.indexOf("6") != -1 || str.indexOf("9") != -1 || i % 3 == 0)
    //         print("0 ")
    //     else
    //         print("$i ")
    // }

    var ans3 = 0
    var ans5 = 0

    repeat(10) {
        val num = br.readLine().toInt()

        if(num % 3 == 0 && num % 5 == 0) {
            ans3++
            ans5++
        }
        else if(num % 3 == 0)
            ans3++
        else if(num % 5 == 0)
            ans5++
    }

    print("$ans3 $ans5")
}