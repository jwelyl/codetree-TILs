import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var st: StringTokenizer

    private lateinit var str : CharArray
    private var q = 0

    fun solve() {
        st = StringTokenizer(br.readLine())
        str = st.nextToken().toCharArray()
        q = st.nextToken().toInt()

        repeat(q) {
            st = StringTokenizer(br.readLine())
            val option = st.nextToken().toInt()

            if(option == 1) {
                val a = st.nextToken().toInt() - 1
                val b = st.nextToken().toInt() - 1

                val tmp = str[a]
                str[a] = str[b]
                str[b] = tmp
            }
            else {
                val from = st.nextToken()[0]
                val to = st.nextToken()[0]

                for(i in 0 until str.size) {
                    if(str[i] == from)
                        str[i] = to
                }
            }

            sb.append(String(str)).append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}