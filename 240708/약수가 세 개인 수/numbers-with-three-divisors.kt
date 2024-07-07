import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var a = 0
    private var b = 0
    private var ans = 0

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        a = tokens.nextToken().toInt()
        b = tokens.nextToken().toInt()
        
        for(num in a .. b) {
            var cnt = 0

            for(d in 1 .. num) {
                if(num % d == 0)
                    cnt++
            }

            if(cnt == 3)
                ans++
        }

        println(ans)
    }
}

fun main() {
    Main().solve()
}