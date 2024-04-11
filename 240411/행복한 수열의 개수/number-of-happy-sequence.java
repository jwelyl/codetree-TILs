import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n, m;
    private static int[][] nums;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        nums = new int[n][n];

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        if(m != 1) {
            //  n개의 행에 대해 행복한 수열인지 확인
            for(int row = 0; row < n; row++) {
                int serial = 1;
                int same = nums[row][0];

                for(int col = 1; col < n; col++) {
                    if(nums[row][col] == same) {
                        serial++;

                        if(serial == m) {   //  row번째 행이 행복한 수열일 경우
                            ans++;
                            break;
                        }
                    }
                    else {
                        same = nums[row][col];
                        serial = 1;
                    }
                }
            }

            //  n개의 열에 대해 행복한 수열인지 확인
            for(int col = 0; col < n; col++) {
                int serial = 1;
                int same = nums[0][col];

                for(int row = 1; row < n; row++) {
                    if(nums[row][col] == same) {
                        serial++;

                        if(serial == m) {   //  col번째 열이 행복한 수열일 경우
                            ans++;
                            break;
                        }
                    }
                    else {
                        same = nums[row][col];
                        serial = 1;
                    }
                }
            }
        }
        else {
            ans = 2 * n;
        }

        System.out.println(ans);
    }
}