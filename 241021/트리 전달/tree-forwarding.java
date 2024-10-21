import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static final int ROOT = 1;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static List<Integer>[] tree;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        dp = new int[N + 1];
        for(int v = 0; v <= N; v++)
            tree[v] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int v = 1; v <= N; v++) {
            int p = Integer.parseInt(st.nextToken());
            if(p != -1)
                tree[p].add(v);
        }

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int add = Integer.parseInt(st.nextToken());

            dp[v] += add;
        }

        dfs(ROOT, 0);

        for(int v = 1; v <= N; v++)
            sb.append(dp[v]).append(" ");
        
        System.out.println(sb);
    }   //  main-end

    private static void dfs(int v, int add) {
        dp[v] += add;

        for(int next : tree[v])
            dfs(next, dp[v]);
    }
}   //  Main-class-end