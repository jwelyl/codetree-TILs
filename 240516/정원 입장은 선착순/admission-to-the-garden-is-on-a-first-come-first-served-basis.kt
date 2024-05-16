import java.util.*
import java.io.*
import kotlin.collections.ArrayList

class Main {
    private val br = BufferedReader(InputStreamReader(System.`in`))
    private lateinit var tokens: StringTokenizer

    private var n = 0

    private val personList = ArrayList<Person>()    //  빨리 도착하는 순서
    private val pq = PriorityQueue<Person>()        //  기다리는 사람 pq

    private var exit = 0 //  들어간 사람이 나오는 시간

    private var maxWait = 0 //  최대 기다린 시간

    fun solve() {
        n = br.readLine().toInt()

        repeat(n) {
            tokens = StringTokenizer(br.readLine())

            personList.add(Person(it + 1, tokens.nextToken().toInt(), tokens.nextToken().toInt()))
        }

        personList.add(Person(n + 1, Int.MAX_VALUE, 0))

        personList.sortWith(compareBy( {it.arrival}, {it.num} ))   //  도착 시간이 빠른 순으로 정렬, 도착 시간이 같을 경우 번호표 번호가 빠른 순으로 정렬

        for(idx in 0 .. n) {
            val person = personList[idx]    // 현재 도착한 사람
            val num = person.num
            val arrival = person.arrival
            val time = person.time

            while(arrival > exit && pq.isNotEmpty()) {  //  현재 도착한 사람 도착 시간이 이전 있던 사람 나오는 시간보다 늦고, 먼저 기다리는 사람이 있을 경우
                val waitPerson = pq.poll()  //  기다리는 사람 중 가장 우선순위 높은 사람이 먼저 들어감

                maxWait = maxOf(maxWait, exit - waitPerson.arrival) //  기다리는 사람이 기다린 시간과 비교
                exit += waitPerson.time //  기다리는 사람이 나올 시간 계산
            }

            if(arrival > exit)  //  먼저 기다리는 사람 없고, 들어간 사람이 나올 시간보다 늦게 도착한 경우
                exit = arrival + time   //  내가 들어가고 나온 시간 계산
            else    //  먼저 기다리는 사람은 없지만 들어간 사람이 나올 시간보다 일찍 도착한 경우
                pq.offer(person)    //  기다리는 사람에 넣기
        }

        println(maxWait)
    }

    private class Person(val num : Int, val arrival : Int, val time : Int) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            return this.num.compareTo(other.num)
        }
    }
}

fun main() {
    Main().solve()
}