import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    
    fun solve() {
        val input1 = br.readLine()
        val input2 = br.readLine()

        if(input1.length > input2.length)
            println("$input1 ${input1.length}")
        else if(input1.length == input2.length)
            println("same")
        else
            println("$input2 ${input2.length}")
    }
}

fun main() {
    Main().solve()
}