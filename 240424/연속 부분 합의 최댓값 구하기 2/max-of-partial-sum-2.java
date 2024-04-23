import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;
    private static int[] nums;

    private static int start = 0;
    private static int end = 0;
    private static int sum = 0;
    private static int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(tokens.nextToken());

        sum = nums[0];

        while(end < n) {
            if(sum < 0) {   //  합이 음수가 될 경우
                sum -= nums[start];
                start++;
                end++;
                if(end < n)
                    sum += nums[end];
            }
            else {  //  합이 0 이상일 경우
                end++;
                if(end < n)
                    sum += nums[end];
            }

            maxSum = Math.max(maxSum, sum);
        }

        System.out.println(maxSum);
    }   //  main-end
}   //  Main-class-end