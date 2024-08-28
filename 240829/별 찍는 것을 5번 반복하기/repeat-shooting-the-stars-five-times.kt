class Main {
    private val sb = StringBuilder()
    
    fun solve() {
        repeat(5) {
            repeat(10) {
                sb.append("*")
            }
            sb.append("\n")
        }

        print(sb)
    }
}

fun main() {
    Main().solve()
}