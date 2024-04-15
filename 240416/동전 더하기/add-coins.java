import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n, k;   //  권종 개수 n, 필요한 금액 k

    private static int[] prices;    //  권종 별 가치
    
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
       
        prices = new int[n];

        for(int i = 0; i < n; i++)
            prices[i] = Integer.parseInt(br.readLine());

        //  가치가 큰 권종부터 사용
        for(int i = n - 1; i >= 0; i--) {
            ans += k / prices[i];    //  필요한 개수

            k %= prices[i]; //  해당 권종으로 지불하고 마저 지불해야 하는 금액

            if(k == 0)  //  지불을 마쳤을 경우
                break;
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end