import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val sb = StringBuilder()
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private var q = 0

    private lateinit var nums : IntArray

    fun solve() {
        tokens = StringTokenizer(br.readLine())
        n = tokens.nextToken().toInt()
        q = tokens.nextToken().toInt()

        nums = IntArray(n + 1)

        tokens = StringTokenizer(br.readLine())
        for(i in 1 .. n)
            nums[i] = tokens.nextToken().toInt()

        repeat(q) {
            tokens = StringTokenizer(br.readLine())

            val cmd = tokens.nextToken().toInt()

            if(cmd == 1) {
                val a = tokens.nextToken().toInt()
                sb.append("${nums[a]}\n")
            }
            else if(cmd == 2) {
                val b = tokens.nextToken().toInt()

                var idx = 0
                for(i in 1 .. n) {
                    if(nums[i] == b) {
                        idx = i
                        break
                    }
                }

                sb.append("$idx\n")
            }
            else {
                val s = tokens.nextToken().toInt()
                val e = tokens.nextToken().toInt()

                for(i in s .. e)
                    sb.append("${nums[i]} ")
                sb.append("\n")
            }
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}