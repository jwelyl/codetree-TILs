import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var st : StringTokenizer

    fun solve() {
        st = StringTokenizer(br.readLine())

        val input1 = st.nextToken()
        val input2 = st.nextToken()

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