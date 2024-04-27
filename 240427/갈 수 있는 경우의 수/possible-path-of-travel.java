import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000007;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[n + 1];
        int[] indegree = new int[n + 1];
        long[] paths = new long[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            indegree[y]++;
        }

        paths[1] = 1; // 시작 노드에서의 경로 수는 1
        Queue<Integer> queue = new LinkedList<>();

        // 위상 정렬 시작: indegree가 0인 노드를 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        // 위상 정렬 처리
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph[current]) {
                paths[next] = (paths[next] + paths[current]) % MOD;
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        System.out.println(paths[n]); // n번 노드로의 경로 수 출력
    }
}