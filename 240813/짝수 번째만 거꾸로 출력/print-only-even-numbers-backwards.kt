import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private val stack = Stack<Char>()
    private var input = ""

    fun solve() {
        input = br.readLine()

        for(idx in input.indices) {
            if(idx % 2 == 1)
                stack.push(input[idx])
        }

        while(stack.isNotEmpty())
            sb.append(stack.pop())

        println(sb)
    }
}

fun main() {
    Main().solve()
}