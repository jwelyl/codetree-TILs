import java.util.*;
import java.io.*;

public class Main {
    private static final int ROOT = 1;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final StringBuilder sb = new StringBuilder();

    private static int n;
    private static List<Integer>[] tree;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++)
            tree[i] = new ArrayList<>();
        parents = new int[n + 1];
        Arrays.fill(parents, -1);

        for(int i = 0; i < n - 1; i++) {
            tokens = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(tokens.nextToken());
            int v2 = Integer.parseInt(tokens.nextToken());

            tree[v1].add(v2);
            tree[v2].add(v1);
        }

        dfs(ROOT, 0);

        for(int i = 2; i <= n; i++)
            sb.append(parents[i]).append("\n");

        System.out.println(sb);
    }   //  main-end

    private static void dfs(int cur, int prev) {
        parents[cur] = prev;

        for(int next : tree[cur]) {
            if(parents[next] == -1)
                dfs(next, cur);
        }
    }
}   //  Main-class-end