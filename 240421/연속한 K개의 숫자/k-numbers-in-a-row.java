import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  1~n까지의 수
    private static int k;   //  연속한 k개의 수
    private static int b;   //  현재 없는 수 개수

    private static int[] nums;  //  nums[i] : 수 i가 있으면 1, 없으면 0

    private static int ans = Integer.MAX_VALUE;    //  연속한 k개의 수가 존재하기 위해 추가해야 할 최소 숫자 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
        b = Integer.parseInt(tokens.nextToken());

        nums = new int[n + 1];
        Arrays.fill(nums, 1);

        for(int i = 1; i <= b; i++) {
            nums[Integer.parseInt(br.readLine())] = 0;
        }

        int start = 1;
        int end = 1;

        int kSum = 0;
        for(int i = 1; i <= k; i++)
            kSum += nums[i];

        ans = Math.min(ans, k - kSum);

        for(int i = 2; i <= n - k; i++) {
            kSum -= nums[i - 1];
            kSum += nums[i + k - 1];
            ans = Math.min(ans, k - kSum);
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end