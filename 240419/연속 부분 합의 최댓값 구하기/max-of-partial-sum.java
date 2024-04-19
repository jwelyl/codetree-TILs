import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static int[] dp;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        dp = new int[n];

        tokens = new StringTokenizer(br.readLine());
        dp[0] = Integer.parseInt(tokens.nextToken());
        max = Math.max(max,dp[0]);

        for(int i = 1; i < n; i++) {
            int num = Integer.parseInt(tokens.nextToken());

            dp[i] = Math.max(dp[i - 1] + num, num);
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }   //  main-end
}   //  Main-class-end