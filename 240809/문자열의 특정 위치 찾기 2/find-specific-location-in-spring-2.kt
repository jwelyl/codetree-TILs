import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private val strs = arrayOf("apple", "banana", "grape", "blueberry", "orange")

    private var cnt = 0

    fun solve() {
        val ch = br.readLine()[0]

        for(str in strs) {
            if(str[2] == ch || str[3] == ch) {
                sb.append(str).append("\n")
                cnt++
            }
        }

        sb.append("$cnt\n")

        print(sb)
    }
}

fun main() {
    Main().solve()
}