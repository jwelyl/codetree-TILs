import java.util.*
import java.io.*

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens : StringTokenizer

    private var n = 0
    private val meetingList = ArrayList<Meeting>()

    private var lastEnd = -1   //  마지막 회의가 끝난 시간
    private var cnt = 0

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            tokens = StringTokenizer(br.readLine())

            meetingList.add(Meeting(tokens.nextToken().toInt(), tokens.nextToken().toInt()))
        }

        meetingList.sort()

        for(meeting in meetingList) {
            if(meeting.start >= lastEnd) {  //  이전 회의 끝난 이후에 현재 회의가 시작할 경우
                cnt++   //  현재 회의 할 수 있음
                lastEnd = meeting.end
            }
        }

        println(cnt)
    }

    private class Meeting(val start : Int, val end : Int) : Comparable<Meeting> {
        override fun compareTo(other : Meeting) : Int {
            var ret = this.end.compareTo(other.end)    //  끝나는 시간이 빠른 회의부터
        
            if(ret == 0)    //  회의 끝나는 시간이 같을 경우
                ret = this.start.compareTo(other.start)    //  시작하는 시간이 빠른 회의부터

            return ret
        }
    }
}

fun main() {
    Main().solve()
}