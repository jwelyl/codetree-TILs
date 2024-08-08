import java.io.*
import java.util.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private val stack = Stack<String>()

    fun solve() {
        repeat(4) {
            stack.append(br.readLine())
        }

        while(stack.isNotEmpty())
            sb.append(stack.pop()).append("\n")

        print(sb)
    }
}

fun main() {
    Main().solve()
}