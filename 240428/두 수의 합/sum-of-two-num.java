import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수
    private static int k;   //  두 수 합 k
    public static List<Integer> nums = new ArrayList<Integer>();    //  수 목록

    private static long ans = 0L;
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nums.add(Integer.parseInt(tokens.nextToken()));

        Collections.sort(nums);

        for(int i = 0; i < n - 1; i++) {
            int num = nums.get(i);
            int target = k - num;

            if(target >= num) {
                int startIndex = Collections.binarySearch(nums, target);
                if(startIndex >= 0) { // target을 찾은 경우
                    // target 값의 첫 위치 찾기
                    while (startIndex > i + 1 && nums.get(startIndex - 1).equals(nums.get(startIndex))) {
                        startIndex--;
                    }
                    // target 값의 마지막 위치 찾기
                    int endIndex = startIndex;
                    while (endIndex < n && nums.get(endIndex).equals(nums.get(startIndex))) {
                        endIndex++;
                    }
                    ans += (endIndex - startIndex);
                }
            }
        }

        System.out.println(ans);
    }   //  main-end                                                                                                                                                                                                                                                                                                                                                             
}   //  Main-class-end