import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private val stack = Stack<Int>()
    
    fun solve() {
        tokens = StringTokenizer(br.readLine())
        
        for(i in 0 ..< tokens.countTokens()) {
            val num = tokens.nextToken().toInt()

            if(num != 0)
                stack.add(num);
            else
                break;
        }

        while(stack.isNotEmpty())
            sb.append("${stack.pop()} ")

        print(sb)
    }
}

fun main() {
    Main().solve()
}