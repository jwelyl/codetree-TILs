import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    fun solve() {
        while(true) {
            val input = br.readLine()

            if(input == "END") {
                print(sb)
                break
            }

            sb.append(StringBuilder(input).reverse().toString()).append("\n")
        }
    }
}

fun main() {
    Main().solve()
}