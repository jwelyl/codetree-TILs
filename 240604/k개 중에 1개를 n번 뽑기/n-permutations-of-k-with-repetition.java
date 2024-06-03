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
            res[nth] = i;
            permutation(nth + 1, res);
        }
    }
}   //  Main-class-end