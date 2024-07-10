import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private val stack = Stack<Int>()
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        while(true) {
            val num = tokens.nextToken().toInt()

            if(num != 0)
                stack.add(num);
        }

        while(stack.isNotEmpty())
            sb.append("${stack.pop()} ")

        print(sb)
    }
}

fun main() {
    Main().solve()
}