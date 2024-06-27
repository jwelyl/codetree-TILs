import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0

    fun solve() {
        n = br.readLine().toInt()

        for(row in 1 .. n) {
            if(row == 1 || row == n) {
                repeat(n) {
                    sb.append("* ")
                }
            }
            else {
                val cnt = row - 1
                repeat(cnt) {
                    sb.append("* ")
                }
                repeat(n - cnt - 1) {
                    sb.append("  ")
                }
                sb.append("* ")
            }

            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}