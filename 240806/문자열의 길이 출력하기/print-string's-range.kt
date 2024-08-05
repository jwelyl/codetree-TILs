import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    
    fun solve() {
        val input1 = br.readLine().length
        val input2 = br.readLine().length

        println(input1 + input2)
    }
}

fun main() {
    Main().solve()
}