import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수
    private static int k;   //  세 수 합 k

    private static final List<Integer> nums = new ArrayList<Integer>();      //  수
    private static long ans = 0L;   //  세 수 합 k 되는 조합 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nums.add(Integer.parseInt(tokens.nextToken()));

        Collections.sort(nums); //  오름차순 정렬

        for(int i = 0; i < n - 2; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                int num1 = nums.get(i);
                int num2 = nums.get(j);
                int target = k - num1 - num2;

                if(target >= num2)  //  찾으려는 수가 num2보다 오른쪽에 존재할 경우
                    ans += countOfNums(target, j + 1, n - 1);
            }
        }

        System.out.println(ans);
    }   //  main-end

    private static int countOfNums(int target, int start, int end) {
        int startIdx = leftBound(target, start, end);   //  nums에서 target의 가장 왼쪽 index
        int endIdx = rightBound(target, start, end);    //  nums에서 target의 가장 오른쪽 index

        if(endIdx == -1 || startIdx == -1)  //  둘 중 하나라도 -1이면
            return 0;   //  target이 존재하지 않음

        return endIdx - startIdx + 1;
    }

    //  [start, end]에서 가장 처음 나타나는 target의 index
    private static int leftBound(int target, int start, int end) {
        int ret = -1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(nums.get(mid) >= target) {   //  target보다 mid값이 더 크거나 같을 경우
                if(nums.get(mid) == target) //  target과 일치할 경우
                    ret = mid;              //  일단 mid 저장

                end = mid - 1;  //  더 왼쪽 찾아봐야 함
            }
            else    //  target보다 mid값이 더 작을 경우 
                start = mid + 1;    //  더 오른쪽 찾아봐야 함
        }

        return ret;
    }

    //  [start, end]에서 가장 나중에 나타나는 target의 index
    private static int rightBound(int target, int start, int end) {
        int ret = -1;

        while(start <= end) {
            int mid = (start + end) / 2;
            
            if(nums.get(mid) <= target) {   //  target보다 mid값이 더 작거나 같을 경우
                if(nums.get(mid) == target) //  target과 일치할 경우
                    ret = mid;              //  일단 mid 저장

                start = mid + 1;   //  더 오른쪽 찾아봐야 함
            }
            else    //  target보다 mid값이 더 클 경우 
                end = mid - 1;    //  더 왼쪽 찾아봐야 함
        }

        return ret;
    }
}   //  Main-class-end