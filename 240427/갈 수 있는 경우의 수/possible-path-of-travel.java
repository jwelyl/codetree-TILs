import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static List<Integer>[] graph;
    private static int[] cnts;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        cnts = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(tokens.nextToken());
            int next = Integer.parseInt(tokens.nextToken());
            graph[prev].add(next);
        }

        // 초기화
        cnts[1] = 1;  // 1번 노드에서 1번 노드로의 경로는 하나뿐임

        // DFS 호출
        dfs(1);

        System.out.println(cnts[n]);
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        for (int next : graph[cur]) {
            if (!visited[next]) {
                dfs(next);
            }
            cnts[next] = (cnts[next] + cnts[cur]) % MOD;
        }
        visited[cur] = false;  // 노드 방문 완료 후 방문 해제
    }
}