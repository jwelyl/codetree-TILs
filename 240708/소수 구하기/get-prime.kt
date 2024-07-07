import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0

    private val isComposite = BooleanArray(101)

    fun solve() {
        init()
        
        a = br.readLine().toInt()

        for(i in 2 .. a) {
            if(!isComposite[i])
                sb.append("$i ")
        }

        println(sb)
    }

    private fun init() {
        for(i in 2 .. 10) {
            if(!isComposite[i]) {
                for(j in i * i .. 100 step i)
                    isComposite[j] = true
            }
        }
    }
}

fun main() {
    Main().solve()
}