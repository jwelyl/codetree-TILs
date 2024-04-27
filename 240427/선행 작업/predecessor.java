import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  친구 수

    private static List<Integer>[] graph;   //  키 순서 비교 그래프
    private static int[] indegrees;         //  진입 차수
    private static int[] times;             //  times[i] : i번째 작업을 끝내는 데 걸리는 시간
    private static int[] endTimes;          //  endTimes[i] : i번째 작업들의 선행 작업을 모두 끝내고 i번째 작업을 끝내는데 걸리는 시간
    private static int maxTime = 0;         //  가장 늦게 끝나는 작업이 끝나는 시간

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        indegrees = new int[n + 1];
        times = new int[n + 1];
        endTimes = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            tokens = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(tokens.nextToken());    //  i번째 작업에 걸리는 시간
            times[i] = time;

            int prevCnt = Integer.parseInt(tokens.nextToken()); //  i번째 작업의 선행 작업 개수
            for(int j = 0; j < prevCnt; j++) {
                int prev = Integer.parseInt(tokens.nextToken());    //  i번째 작업의 선행 작업 prev

                graph[prev].add(i);
                indegrees[i]++;
            }
        }

        topologicalSort();
    }   //  main-end

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i <= n; i++) {
            if(indegrees[i] == 0) { //  선행 작업이 없는 작업들
                q.offer(i);
                endTimes[i] = times[i];
                maxTime = Math.max(maxTime, endTimes[i]);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                indegrees[next]--;
                endTimes[next] = Math.max(endTimes[next], endTimes[cur]);    //  next가 완전히 끝나려면 cur 작업이 먼저 끝나야 함, 선행 작업들 중 가장 오래 걸리는 작업 기준으로 함

                if(indegrees[next] == 0) {
                    q.offer(next);
                    endTimes[next] += times[next];  //  next의 선행 작업들이 모두 끝나고, next 자체의 작업 시간 추가
                    maxTime = Math.max(maxTime, endTimes[next]);
                }
            }
        }
        
        // for(int i = 1; i <= n; i++)
        //     System.out.print(endTimes[i] + " ");
        // System.out.println();

        System.out.println(maxTime);
    }
}   //  Main-class-end