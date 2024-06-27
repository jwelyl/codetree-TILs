import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        for(row in 1 .. n) {
            for(col in 1 .. n)
                sb.append(row)
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}