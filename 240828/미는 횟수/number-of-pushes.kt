import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var strA = ""
    private var strB = ""

    fun solve() {
        strA = br.readLine()
        strB = br.readLine()

        for(i in 0 until strA.length) {
            strA = strA[strA.length - 1] + strA.substring(0, strA.length - 1)
            if(strA == strB) {
                println(i + 1)
                return
            }
        } 

        println(-1)
    }
}

fun main() {
    Main().solve()
}