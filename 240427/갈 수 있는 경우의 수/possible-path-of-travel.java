import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1_000_000_007;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    
    private static int n;   //  노드 개수
    private static int m;   //  간선 개수

    private static List<Integer>[] graph;   //  그래프
    private static int[] indegree;  //  진입 차수
    private static int[] paths;     //  paths[i] : 1번 정점에서 i번째 정점까지 가는 경로 수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList[n + 1];
        indegree = new int[n + 1];
        paths = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
        
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());

            graph[x].add(y);
            indegree[y]++;
        }

        paths[1] = 1; // 시작 노드에서의 경로 수는 1
    
        topologicalSort();
    }   // main-end

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        // 위상 정렬 시작: indegree가 0인 노드를 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // 위상 정렬 처리
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph[cur]) {
                paths[next] = (paths[next] + paths[cur]) % MOD;
                indegree[next]--;
                if (indegree[next] == 0)
                    q.add(next);
            }
        }

        System.out.println(paths[n]); // n번 노드로의 경로 수 출력
    }
}   //  Main-class-end