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

            if(target >= num)
                ans += binarySearch(target, i + 1, n - 1);
        }

        System.out.println(ans);
    }   //  main-end

    //  [start, end] 범위에서 target의 개수
    private static int binarySearch(int target, int start, int end) {
        if(start > end) //  더 이상 없을 경우
            return 0;
        
        int mid = (start + end) / 2;
        int ret = nums.get(mid) == target ? 1 : 0;

        ret += binarySearch(target, start, mid - 1);    //  [start, mid - 1]에 몇 개 있나 찾아보기
        ret += binarySearch(target, mid + 1, end);      //  [mid + 1, end]에 몇 개 있나 찾아보기

        return ret;
    }                                                                                                                                                                                                                                                                                                                                                                
}   //  Main-class-end