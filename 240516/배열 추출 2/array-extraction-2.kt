import java.util.*
import java.io.*
import kotlin.math.abs

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()

    private var n = 0
    private val pq = PriorityQueue<Item>()

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            val x = br.readLine().toInt()

            if(x == 0) {
                if(pq.isEmpty())
                    sb.append("0\n")
                else
                    sb.append("${pq.poll().num}\n")
            }
            else
                pq.offer(Item(x))
        }

        print(sb)
    }

    private class Item(val num : Int) : Comparable<Item> {
        override fun compareTo(other : Item) : Int {
            var ret = abs(this.num).compareTo(abs(other.num))

            if(ret == 0)
                ret = this.num.compareTo(other.num)

            return ret
        }
    }
}

fun main() {
    Main().solve()
}