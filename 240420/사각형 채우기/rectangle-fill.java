import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 10_007;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int n;
    private static int[] dp;
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];

        dp[1] = 1;

        if(n >= 2)
            dp[2] = 2;

        for(int i = 3; i <= n; i++)
            dp[i] = (dp[i - 1] % MOD + dp[i - 2] % MOD) % MOD;

        System.out.println(dp[n]);
    }   //  main-end
}   //  Main-class-end