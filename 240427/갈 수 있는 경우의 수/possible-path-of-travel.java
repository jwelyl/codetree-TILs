import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  노드 수
    private static int m;   //  간선 수

    private static List<Integer>[] graph;   //  키 순서 비교 그래프
    private static int[] cnts;              //  cnts[i] : 1번 노드에서 출발해서 i번 노드로 이동 가능한 서로 다른 경로의 수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        cnts = new int[n + 1];

        for(int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            
            int prev = Integer.parseInt(tokens.nextToken());
            int next = Integer.parseInt(tokens.nextToken());

            graph[prev].add(next);
        }

        dfs(1, 1);

        System.out.println(cnts[n]);
    }   //  main-end

    private static void dfs(int cur, int add) {
        cnts[cur] = (cnts[cur] % MOD + add % MOD) % MOD;

        for(int next : graph[cur])
            dfs(next, cnts[cur]);
    }
}   //  Main-class-end