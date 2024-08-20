import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private var str = ""
    private var q = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        str = st.nextToken()
        q = st.nextToken().toInt()

        repeat(q) {
            val cmd = br.readLine().toInt()

            if(cmd == 1)
                str = str.substring(1, str.length) + str[0]
            else if(cmd == 2)
                str = str[str.length - 1] + str.substring(0, str.length - 1)
            else
                str = StringBuilder(str).reverse().toString()

            sb.append("$str\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}