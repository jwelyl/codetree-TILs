import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  원소 개수
    private static int s;   //  원소 합 s

    private static int[] nums;  //  원소

    private static int start = 0;
    private static int end = 0;
    private static int sum = 0;

    private static int len = Integer.MAX_VALUE; //  원소들 합이 s 이상이 되는 가장 짧은 구간 길이
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        s = Integer.parseInt(tokens.nextToken());

        nums = new int[n];
        tokens = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(tokens.nextToken());

        sum = nums[end];

        while(start <= end && end < n) {
            if(sum >= s) {   // 구간 합이 s 이상일 경우
                // System.out.println("start = " + start + ", end = " + end);
                // System.out.println("sum = " + sum);

                len = Math.min(len, end - start + 1);
                sum -= nums[start];
                start++;
            }
            else {  //  구간 합이 s 미만일 경우
                end++;
                if(end < n)
                    sum += nums[end];
            }
        }

        System.out.println(len == Integer.MAX_VALUE ? -1 : len);
    }   //  main-end
}   //  Main-class-end