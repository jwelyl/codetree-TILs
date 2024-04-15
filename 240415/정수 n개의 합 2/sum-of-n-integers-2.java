import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n = 0;
    private static int k = 0;
    private static int[] nums;
    private static int sum = 0;
    private static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        nums = new int[n];

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(tokens.nextToken());

            if(i < k)
                sum += nums[i];
        }

        ans = Math.max(ans, sum);

        for(int end = k; end < n; end++) {
            int start = end - k + 1;

            sum -= nums[start - 1];
            sum += nums[end];

            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end