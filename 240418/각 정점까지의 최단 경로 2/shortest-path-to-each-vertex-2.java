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
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for(int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(tokens.nextToken());
            int v2 = Integer.parseInt(tokens.nextToken());

            //  v1에서 v2로 가는 길이 이미 존재할 경우, 더 짧은 길로 결정
            dp[v1][v2] = Math.min(dp[v1][v2], Integer.parseInt(tokens.nextToken()));
        }

        floydWarshall();

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(dp[i][j] == Integer.MAX_VALUE)
                    sb.append(-1).append(" ");
                else
                    sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }   //  main-end
 
    private static void floydWarshall() {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }
}   //  Main-class-end