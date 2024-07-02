import java.util.*
import java.io.*

const val MAX = 19

public class Main {
    private val sb = StringBuilder()

    fun solve() {
        for(left in 1 .. MAX) {
            for(start in 1 .. 10) {
                for(right in 2 * start - 1 .. minOf(2 * start, MAX)) {
                    if(right % 2 == 0)
                        sb.append(" / ")
                    sb.append("$left * $right = ${left * right}")
                }
                sb.append("\n")                    
            }
        }
    
        print(sb)
    }
}

fun main() {
    Main().solve()
}