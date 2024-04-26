import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n = 0;
    private static int[] nums;

    private static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        nums = new int[n];
        tokens = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(tokens.nextToken());

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                for(int k = j + 1; k < n; k++) {
                    int num1 = nums[i];
                    int num2 = nums[j];
                    int num3 = nums[k];

                    if((num1 & num2) == 0 && (num2 & num3) == 0 && (num3 & num1) == 0)
                        maxSum = Math.max(maxSum, num1 + num2 + num3);
                }
            }
        }

        System.out.println(maxSum);
    }   //  main-end
}   //  Main-class-end