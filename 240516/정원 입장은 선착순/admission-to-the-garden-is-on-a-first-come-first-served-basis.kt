import java.util.*
import java.io.*
import kotlin.collections.ArrayList

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens: StringTokenizer

    private var n = 0

    private val personList = ArrayList<Person>()
    private val pq = PriorityQueue<Person>()

    private var nowTime = 0 //  들어갈 수 있는 시간
    private var idx = 0

    private var maxWait = 0 //  최대 기다린 시간

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            tokens = StringTokenizer(br.readLine())

            personList.add(Person(it + 1, tokens.nextToken().toInt(), tokens.nextToken().toInt()))
        }

//        println("personList = $personList")

        personList.sortWith(compareBy( {it.arrival}, {it.num} ))   //  도착 시간이 빠른 순으로 정렬, 도착 시간이 같을 경우 번호표 번호가 빠른 순으로 정렬

//        println("personList = $personList")

        while(idx < n) {
            if(personList[idx].arrival >= nowTime) {  //  가장 빨리 도착하는 사람이 들어갈 수 있는 시간 이후에 도착할 경우
                nowTime = personList[idx].arrival + personList[idx].time    //  들어갈 수 있는 시간 갱신
                idx++
            }
            else {
                while(idx < n && nowTime >= personList[idx].arrival) //  기다려야 하는 사람들 모두 pq에 넣기
                    pq.offer(personList[idx++])

                while(pq.isNotEmpty()) {    //  번호표 번호 작은 사람부터 뽑기
                    val person = pq.poll()

//                    println(person)
                    val wait = nowTime - person.arrival
//                    println("wait = $wait")

                    maxWait = maxOf(maxWait, nowTime - person.arrival) //  해당 사람이 기다려야 하는 시간과 비교해서 최대로 기다려야 하는 시간 갱신
                    nowTime += person.time
                }
            }
        }

        println(maxWait)
    }

    private class Person(val num : Int, val arrival : Int, val time : Int) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return this.num.compareTo(other.num)
        }

        override fun toString(): String {
            return "($num, $arrival, $time)"
        }
    }
}

fun main() {
    Main().solve()
}