import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final StringBuilder sb = new StringBuilder();

    private static int n, m;   //  정렬된 수 개수 n, 찾을 수 개수 m

    private static int[] nums;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        
        nums = new int[n];

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(tokens.nextToken());

        Arrays.sort(nums);

        for(int i = 0; i < m; i++)
            sb.append(binarySearch(Integer.parseInt(br.readLine()))).append("\n");

        System.out.print(sb);
    }   //  main-end

    private static int binarySearch(int target) {
        int idx = -1;   //  찾지 못했을 경우 -1 반환
        int start = 0;
        int end = n - 1;

        while(start <= end) {
            int mid = (start + end) / 2;
            int midNum = nums[mid];
            
            if(midNum == target) {  //  중앙값이 target일 경우
                idx = (mid + 1);
                break;
            }
            else if(midNum < target)    //  중앙값이 target보다 작을 경우
                start = mid + 1;    //  오른쪽에서 찾기
            else                        //  중앙값이 target보다 클 경우
                end = mid - 1;      //  왼쪽에서 찾기
        }

        return idx;
    }
}   //  Main-class-end