import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  원소 개수
    private static int m;   //  원소 합 s

    private static int[] nums;  //  원소

    private static int start = 0;
    private static int end = 0;
    private static int sum = 0;

    private static int ans = 0; //  원소들 합이 m이 되는 경우의 수
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        nums = new int[n];
        tokens = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(tokens.nextToken());

        sum = nums[end];

        while(start <= end && end < n) {
            if(sum == m) {
                ans++;
                sum -= nums[start];
                start++;
                end++;
                if(end < n)
                    sum += nums[end];
            }
            else if(sum > m) {
                sum -= nums[start];
                start++;
            }
            else {
                end++;
                if(end < n)
                    sum += nums[end];
            }
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end