import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  정사각형 크기
    private static int k;   //  구간 합 k

    private static int[] prefixSum;   //  누적합

    private static int ans = 0;    //  부분합 k인 구간 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        prefixSum = new int[n + 1];

        tokens = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(tokens.nextToken());
        }

        int start = 1;
        int end = 1;

        while(end <= n) {
            int partialSum = partialSum(start, end);

            //  부분합이 모자란다면
            if(partialSum < k)
                end++;
            //  부분합이 일치한다면
            else if(partialSum == k) {
                ans++;
                start++;
                end++;
            }
            //  부분합이 초과한다면
            else {
                start++;
                if(start > end)
                    end = start;
            }
        }

        System.out.println(ans);
    }   //  main-end

    //  [from. to] 구간 합
    private static int partialSum(int from, int to) {
        return prefixSum[to] - prefixSum[from - 1];
    }
}   //  Main-class-end