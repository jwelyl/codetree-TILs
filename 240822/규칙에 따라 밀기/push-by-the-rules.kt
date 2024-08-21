import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""
    private var cmd = ""

    fun solve() {
        str = br.readLine()
        cmd = br.readLine()

        for(dir in cmd) {
            if(dir == 'L')
                str = str.substring(1, str.length) + str[0]
            else
                str = str[str.length - 1] + str.substring(0, str.length - 1)
        }

        print(str)
    }
}

fun main() {
    Main().solve()
}