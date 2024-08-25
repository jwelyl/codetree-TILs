import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st: StringTokenizer

    private val num1 = StringBuilder()
    private val num2 = StringBuilder()
    private var str1 = ""
    private var str2 = ""

    fun solve() {
        str1 = br.readLine()
        str2 = br.readLine()

        for(ch in str1) {
            if(ch !in '0' .. '9')
                break
            num1.append(ch)
        }

        for(ch in str2) {
            if(ch !in '0' .. '9')
                break
            num2.append(ch)
        }

        println(num1.toString().toInt() + num2.toString().toInt())
    }
}

fun main() {
    Main().solve()
}