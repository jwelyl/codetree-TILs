import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  원소 개수
    private static int k;   //  원소 합 k

    private static int[] nums;  //  원소

    private static int start = 0;
    private static int end = 0;

    private static int ans = 0; //  원소들 합이 m이 되는 경우의 수
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        nums = new int[n];

        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(br.readLine());

        Arrays.sort(nums);

        start = 0;
        end = n - 1;

        while(start < end) {
            if(nums[start] + nums[end] > k)    //  두 수 합이 k를 넘을 경우
                end--;  //  합을 줄여야 함
            else {
                ans++;
                
                if(nums[start] + nums[end] == k)  //  두 수 합이 딱 k일 경우
                    end--;  //  end를 줄여서 합을 줄여야 함
                else
                    start++;  //  start를 늘려서 합을 키워봄
            }
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end