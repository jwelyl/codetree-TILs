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

       repeat(n) {
          tokens = StringTokenizer(br.readLine())
          val p0 = tokens.nextToken().toLong()
          val v = tokens.nextToken().toLong()
          val p = p0 + v * t

          val prev = treeSet.ceiling(p)

          if(prev != null) // 따라잡힐 경우
            treeSet.remove(prev)

          treeSet.add(p)
       }

       println(treeSet.size)
    }
}

fun main() {
    Main().solve()
}