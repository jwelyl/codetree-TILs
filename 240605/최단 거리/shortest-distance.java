import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;
    private static int m;

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        dp = new long[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++)
                dp[i][j] = Integer.parseInt(tokens.nextToken());
        }

        floydWarshall();

        for(int q = 0; q < m; q++) {
            tokens = new StringTokenizer(br.readLine());

            sb.append(dp[Integer.parseInt(tokens.nextToken())][Integer.parseInt(tokens.nextToken())]).append("\n");
        }

        System.out.print(sb);
    }   //  main-end

    private static void floydWarshall() {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
            }
        }
    }
}   //  Main-class-end