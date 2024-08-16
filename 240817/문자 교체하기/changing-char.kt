import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private lateinit var str1 : CharArray
    private lateinit var str2 : CharArray

    fun solve() {
        st = StringTokenizer(br.readLine())
        str1 = st.nextToken().toCharArray()
        str2 = st.nextToken().toCharArray()

        str2[0] = str1[0]
        str2[1] = str1[1]

        println(String(str2))
    }
}

fun main() {
    Main().solve()
}