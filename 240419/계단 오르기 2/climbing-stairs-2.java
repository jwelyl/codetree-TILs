import java.util.*;
import java.io.*;

public class Main {
    private static final int NONE = Integer.MIN_VALUE;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static int[] coins;
    private static int[][] dp;
    private static int max = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        coins = new int[n + 1];
        dp = new int[4][n + 1];
        for(int i = 0; i <= 3; i++)
            Arrays.fill(dp[i], NONE);

        dp[0][0] = 0;

        tokens = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            coins[i] = Integer.parseInt(tokens.nextToken());

        for(int i = 1; i <= n; i++) {
            //  i-1계단에서 1 계단 더 올라오는 경우
            for(int j = 0; j <= 2; j++) {
                if(dp[j][i - 1] != NONE)
                    dp[j + 1][i] = dp[j][i - 1] + coins[i];
            }

            //  i-2계단에서 2 계단 더 올라오는 경우
            for(int j = 0; j <= 3; j++) {
                if(i >= 2 && dp[j][i - 2] != NONE)
                    dp[j][i] = Math.max(dp[j][i], dp[j][i - 2] + coins[i]);
            }
        }

        for(int i = 0; i <= 3; i++) {
            for(int j = 1; j <= n; j++)
                max= Math.max(max, dp[i][j]);
        }

        System.out.println(max);
    }   //  main-end
}   //  Main-class-end