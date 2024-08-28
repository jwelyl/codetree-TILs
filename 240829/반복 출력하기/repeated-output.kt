import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    
    private var n = 0
    
    fun solve() {
        n = br.readLine().toInt()
        
        repeat(n) {
            sb.append("12345^&*()_\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}