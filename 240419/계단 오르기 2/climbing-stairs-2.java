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
            //  i-1번째 계단에서 1 계단 더 오르는 방법
            for(int j = 0; j < 3; j++) {    //  i-1번째 계단까지는 1계단 오르기를 총 j번 함
                if(dp[j][i - 1] != NONE)    //  i-1번째 계단까지는 1계단 오르기 j번으로 오르는 게 가능할 경우
                    dp[j + 1][i] = Math.max(dp[j + 1][i], dp[j][i - 1] + coins[i]);
            }

            //  i-2번째 계단에서 2 계단 더 오르는 방법
            if(i >= 2) {
                for(int j = 0; j <= 3; j++) {   //  i-2번째 계단까지는 1계단 오르기를 총 j번 함
                    if(dp[j][i - 2] != NONE)    //  i-2번째 계단까지는 1계단 오르기를 총 j번으로 오르는 게 가능할 경우
                        dp[j][i] = Math.max(dp[j][i], dp[j][i - 2] + coins[i]);
                }
            }
        }

        for(int i = 0; i <= 3; i++) {
            for(int j = 1; j <= n; j++)
                max= Math.max(max, dp[i][j]);
        }

        System.out.println(max);
    }   //  main-end
}   //  Main-class-end