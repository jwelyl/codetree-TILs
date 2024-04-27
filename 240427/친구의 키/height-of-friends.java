import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  친구 수
    private static int m;   //  키 비교 횟수

    private static List<Integer>[] graph;   //  키 순서 비교 그래프
    private static int[] indegrees;         //  진입 차수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        graph = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
            graph[i] = new ArrayList<>();

        indegrees = new int[n + 1];

        for(int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());

            graph[a].add(b);
            indegrees[b]++;
        }

        topologicalSort();
    }   //  main-end

    private static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= n; i++) {
            if(indegrees[i] == 0) {
                q.offer(i);
                sb.append(i).append(" ");
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph[cur]) {
                indegrees[next]--;

                if(indegrees[next] == 0) {
                    q.offer(next);
                    sb.append(next).append(" ");
                }
            }
        }

        System.out.print(sb);
    }
}   //  Main-class-end