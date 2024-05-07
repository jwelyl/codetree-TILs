import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
  private static final int NONE = Integer.MAX_VALUE;
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n; //  수의 개수
  private static int m; //  두 수의 차이가 m 이상이어야 함

  private static final TreeSet<Integer> treeSet = new TreeSet<>();
  private static int[] nums;  //  주어진 수
  private static int ans = NONE;  //  두 수의 차이가 m 이상이면서 최소인 차이, m 이상인 차이가 없으면 NONE

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    nums = new int[n];
    for(int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(br.readLine());
      treeSet.add(nums[i]);
    }

    for(int i = 0; i < n; i++) {
      int num = nums[i];

      Integer left = treeSet.floor(num - m);      //  num보다 m 이상 작으면서 가장 큰 수
      Integer right = treeSet.ceiling(num + m);   //  num보다 m 이상 크면서 가장 작은 수

      int leftDiff = left == null ? NONE : num - left;
      int rightDiff = right == null ? NONE : right - num;

      ans = Math.min(ans, Math.min(leftDiff, rightDiff));
    }

    System.out.println(ans == NONE ? -1 : ans);
  } // main-end
} //  Main-class-end