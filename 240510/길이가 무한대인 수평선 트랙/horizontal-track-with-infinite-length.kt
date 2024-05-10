import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0  //  사람 수
    private var t = 0  //  뛰는 시간

    private val treeSet = TreeSet<Long>()  //  t분 후 사람 위치

    fun solve() {
       tokens = StringTokenizer(br.readLine())
       n = tokens.nextToken().toInt()
       t = tokens.nextToken().toInt()

       treeSet.add(Long.MAX_VALUE)

       
    }
}

fun main() {
    Main().solve()
}