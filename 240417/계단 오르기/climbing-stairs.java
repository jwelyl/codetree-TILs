import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 10_007;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final int[] dp = new int[1_001];

    private static int n = 0;

    public static void main(String[] args) throws IOException {
        dp[2] = 1;
        dp[3] = 1;

        for(int i = 4; i <= 1_000; i++)
            dp[i] = (dp[i - 2] + dp[i - 3]) % MOD;

        n = Integer.parseInt(br.readLine());

        System.out.println(dp[n]);
    }   //  main-end
}   //  Main-class-end