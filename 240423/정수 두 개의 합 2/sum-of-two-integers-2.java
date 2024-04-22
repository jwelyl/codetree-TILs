import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  원소 개수
    private static int k;   //  원소 합 k

    private static int[] nums;  //  원소

    private static int ans = 0; //  원소들 합이 m이 되는 경우의 수
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        nums = new int[n];

        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        for(int i = 0; i < n - 1; i++) {
            int num = nums[i];

            if(k - num < num)
                break;

            int from = i + 1;
            int to = n - 1;
            int target = k - num;

            // System.out.println("target = " + target);
            // System.out.println("from = " + from);
            // System.out.println("to = " + to);


            ans += binarySearch(from, to, target);  //  [from, to] 범위에서 target 이하 정수 개수 찾기
        }

        System.out.println(ans);
    }   //  main-end

    private static int binarySearch(int from, int to, int target) {
        int idx = -1;

        int start = from;
        int end = to;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(nums[mid] <= target) {
                idx = mid;
                start = mid + 1;
            }
            else end = mid - 1;
        }

        // System.out.println("idx = " + idx);

        return idx == -1 ? 0 : idx - from + 1;
    }
}   //  Main-class-end