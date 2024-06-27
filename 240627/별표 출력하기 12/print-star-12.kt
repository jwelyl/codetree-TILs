import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0
    private val stars = Array(16) { CharArray(16) { ' ' } }

    fun solve() {
        n = br.readLine().toInt()

        for(col in 1 .. n) {
            if(col % 2 == 1)
                stars[1][col] = '*'
            else {
                for(row in 1 .. col)
                    stars[row][col] = '*'
            }
        }

        for(row in 1 .. n) {
            for(col in 1 .. n)
                sb.append("${stars[row][col]} ")
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}