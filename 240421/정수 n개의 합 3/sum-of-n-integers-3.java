import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  정사각형 크기
    private static int k;   //  부분 정사각형 크기

    private static int[][] prefixSum;   //  정사각형 누적합

    private static int ans = -1;    //  크기 k * k인 부분 정사각형 합 최대

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        prefixSum = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i][j - 1] + prefixSum[i - 1][j] 
                                - prefixSum[i - 1][j - 1] + Integer.parseInt(tokens.nextToken());
            }
        }

        for(int i = 1; i <= n - k + 1; i++) {
            for(int j = 1; j <= n - k + 1; j++) {
                int sy = i;
                int sx = j;
                int ey = sy + k - 1;
                int ex = sx + k - 1;

                int partialSum = prefixSum[ey][ex] 
                            - prefixSum[sy - 1][ex] - prefixSum[ey][sx - 1] 
                            + prefixSum[sy - 1][sx - 1];

                ans = Math.max(ans, partialSum);
            }
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end