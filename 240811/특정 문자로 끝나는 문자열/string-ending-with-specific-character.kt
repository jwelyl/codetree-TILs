import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private val list = ArrayList<String>()
    private var key = ' '

    fun solve() {
        repeat(10) {
            list.add(br.readLine())
        }

        key = br.readLine()[0]

        var ok = false
        for(str in list) {
            if(str[str.length - 1] == key) {
                sb.append(str).append("\n")
                ok = true
            }
        }
        
        if(ok)
            print(sb)
        else
            println("None")
    }
}

fun main() {
    Main().solve()
}