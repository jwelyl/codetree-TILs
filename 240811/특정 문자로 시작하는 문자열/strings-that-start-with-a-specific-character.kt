import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st : StringTokenizer

    private var n = 0
    private val list = ArrayList<String>()
    private var key = ' '
    private var cnt = 0
    private var sum = 0.0

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            list.add(br.readLine())
        }

        key = br.readLine()[0]

        for(str in list) {
            if(str[0] == key) {
                sum += str.length
                cnt++
            }
        }
        
        println(String.format("%d %.2f", cnt, sum / cnt))
    }
}

fun main() {
    Main().solve()
}