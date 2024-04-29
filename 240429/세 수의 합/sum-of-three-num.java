import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수
    private static int k;   //  세 수 합 k

    private static final List<Integer> nums = new ArrayList<>();
    private static final Map<Integer, Integer> numMap = new HashMap<>();      //  Key : 수, Value : 수의 등장 횟수
    private static long ans = 0L;   //  세 수 합 k 되는 조합 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            nums.add(num);

            numMap.put(num, numMap.getOrDefault(num, 0) + 1);    //  num의 등장 횟수 증가
        }

        for(int i = 0; i < n; i++) {
            int num1 = nums.get(i); //  첫 번째 수

            if(numMap.containsKey(num1)) {
                numMap.put(num1, numMap.get(num1) - 1); //  등장 횟수 1 감소 (중복해서 세지 않도록)

                if(numMap.get(num1) == 0)   //  더 이상 등장하지 않을 경우
                    numMap.remove(num1);    //  num1을 해시맵에서 제거
            }
        
            for(int j = 0; j < i; j++) {
                int num2 = nums.get(j); //  두 번째 수
                int target = k - num1 - num2;
                
                if(numMap.containsKey(target))
                    ans += numMap.get(target);
            }
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end