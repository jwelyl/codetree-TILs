import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private val stack = Stack<Int>()
    
    fun solve() {
        n = br.readLine().toInt()

        tokens = StringTokenizer(br.readLine())
        repeat(n) {
            val num = tokens.nextToken().toInt()

            if(num % 2 == 0)
                stack.push(num)
        }

        while(stack.isNotEmpty())
            sb.append(stack.pop()).append(" ")

        println(sb)
    }
}

fun main() {
    Main().solve()
}