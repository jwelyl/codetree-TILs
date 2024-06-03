import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int k;
    private static int n;
    
    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        k = Integer.parseInt(tokens.nextToken());
        n = Integer.parseInt(tokens.nextToken());

        permutation(0, new int[n]);

        System.out.print(sb);
    }   //  main-end

    private static void permutation(int nth, int[] res) {
        if(nth == n) {
            for(int i = 0; i < n; i++)
                sb.append(res[i]).append(" ");
            sb.append("\n");

            return;
        }

        for(int i = 1; i <= k; i++) {
            if(nth >= 2) {  //  앞에 수가 2개 이상 있을 경우
                if(res[nth - 2] == res[nth - 1] && res[nth - 1] == i)   //  현재 수와 앞의 두 수가 같을 경우
                    continue;   //  이 수는 놓을 수 없으므로 건너뛰기
            }
            
            res[nth] = i;

            permutation(nth + 1, res);
        }
    }
}   //  Main-class-end