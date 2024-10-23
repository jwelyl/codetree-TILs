import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int K;

    private static int[] nums;

    private static int minLen = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int start = 0;
        int end = 0;
        int cnt = nums[end] == 1 ? 1 : 0;    //  [start, end]에 포함된 1의 개수
        int len = 1;

        if(cnt >= K)
            minLen = Math.min(minLen, len);

        while(true) {
            if(cnt < K) {   //  [start, end] 구간의 1의 개수가 부족할 경우, end를 키워서 1의 개수를 키워야 함
                if(end + 1 < N) {   //  다음 수가 존재해서 end를 키울 수 있을 경우
                    cnt += nums[end + 1] == 1 ? 1 : 0;
                    end++;
                }
                else break; //  더 늘릴 수 없으므로 종료
            }
            else {  //  1의 개수가 충분할 경우
                len = end - start + 1;  //  [start, end] 구간 길이
                minLen = Math.min(minLen, len);

                cnt -= nums[start] == 1 ? 1 : 0;    //  start를 키워서 구간 길이 줄이기
                start++;
            }
        }

        System.out.println(minLen == Integer.MAX_VALUE ? -1 : minLen);
    }   //  main-end
}   //  Main-class-end