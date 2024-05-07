import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  private static final int NONE = Integer.MAX_VALUE;
  private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer tokens;
  private static int n; //  수의 개수
  private static int m; //  두 수의 차이가 m 이상이어야 함

  private static int[] nums;  //  주어진 수
  private static int ans = NONE;  //  두 수의 차이가 m 이상이면서 최소인 차이, m 이상인 차이가 없으면 NONE

  public static void main(String[] args) throws IOException {
    tokens = new StringTokenizer(br.readLine());

    n = Integer.parseInt(tokens.nextToken());
    m = Integer.parseInt(tokens.nextToken());

    nums = new int[n];
    for(int i = 0; i < n; i++)
      nums[i] = Integer.parseInt(br.readLine());

    Arrays.sort(nums);

    int start = 0;
    int end = 1;

    while(start < end && end < n) {
      int diff = nums[end] - nums[start];

      if(diff >= m) {  //  두 수의 차이가 m 이상이면
        ans = Math.min(ans, nums[end] - nums[start]); //  두 수의 차이 더 작은 값으로 갱신
        start++;  //  더 작은 차이가 있나 확인하기 위해 start 키워서 차이 줄여보기
        if(start == end)  //  만약 start가 end를 따라잡으면 둘이 같은 수가 됨
          end++;  //  end 더 키워서 서로 다른 수가 되게 하기
      }
      else //  두 수 차이가 m 미만이면
        end++;  //  더 큰 차이가 있나 확인하기 위해 end 키워서 차이 크게 하기
    }

    System.out.println(ans == NONE ? -1 : ans);
  } // main-end
} //  Main-class-end