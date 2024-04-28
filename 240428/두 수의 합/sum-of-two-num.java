import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수
    private static int k;   //  두 수 합 k
    private static final List<Integer> nums = new ArrayList<Integer>();    //  수 목록
    private static final Map<Integer, Integer> map = new HashMap<>();      //  Key : 수, Value : 해당 수의 개수

    private static long ans = 0L;
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            nums.add(Integer.parseInt(tokens.nextToken()));

        for(int i = 0; i < n; i++) {
            int num = nums.get(i);
            int target = k - num;

            //  이전 수 중에 target이 존재할 경우
            if(map.containsKey(target))
                ans += map.get(target);

            //  이전 수 중에 존재했던 num 개수 + 1
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        System.out.println(ans);
    }   //  main-end                                                                                                                                                                                                                                                                                                                           
}   //  Main-class-end