import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1_000_000_007;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int n;
    private static long[] dp;
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        dp = new long[n + 1];

        dp[1] = 2;

        if(n >= 2)
            dp[2] = 7;

        dp[0] = 1;
        dp[1] = 2;

        for(int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] * 2 + dp[i - 2] * 3) % MOD;
            for(int j = i - 3; j >= 0; j--)
                dp[i] = (dp[i] + dp[j] * 2) % MOD;
        }

        System.out.println(dp[n]);
    }   //  main-end
}   //  Main-class-end