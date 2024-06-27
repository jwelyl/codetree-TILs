import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        for(row in n downTo 1) {
            for(col in n downTo 1)
                sb.append("$col ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}