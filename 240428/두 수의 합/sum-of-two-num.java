import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수
    private static int k;   //  두 수 합 k
    public static final List<Integer> nums = new ArrayList<Integer>();    //  수 목록

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
                ans += countOccurrences(target, i + 1, n - 1);
        }

        System.out.println(ans);
    }   //  main-end

    public static int countOccurrences(int target, int start, int end) {
        int firstIndex = findFirstIndex(target, start, end);
        int lastIndex = findLastIndex(target, start, end);
        
        if (firstIndex == -1 || lastIndex == -1) 
            return 0;
        
        return lastIndex - firstIndex + 1;
    }

    private static int findFirstIndex(int target, int start, int end) {
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) >= target) {
                if (nums.get(mid) == target) index = mid;
                end = mid - 1;
            } else
                start = mid + 1;
        }
        return index;
    }

    private static int findLastIndex(int target, int start, int end) {
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums.get(mid) <= target) {
                if (nums.get(mid) == target) index = mid;
                start = mid + 1;
            } else
                end = mid - 1;
        }
        return index;
    }                                                                                                                                                                                                                                                                                                                                                               
}   //  Main-class-end