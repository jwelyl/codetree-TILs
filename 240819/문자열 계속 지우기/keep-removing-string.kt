import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str1 = ""
    private var str2 = ""

    fun solve() {
        str1 = br.readLine()
        str2 = br.readLine()

        while(true) {
            val idx = str1.indexOf(str2)

            if(idx == -1) {
                println(str1)
                break
            }

            str1 = str1.substring(0, idx) + str1.substring(idx + str2.length) 
        }
    }
}

fun main() {
    Main().solve()
}