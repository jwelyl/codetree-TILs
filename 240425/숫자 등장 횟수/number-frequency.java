import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;
    
    private static int n;   //  원소 개수
    private static int m;   //  질의 개수

    private static final Map<Integer, Integer> map = new HashMap<>();   //  K : 수열의 수, V : 수열의 수의 등장 횟수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            int cnt = map.getOrDefault(num, 0);

            map.put(num, cnt + 1);
        }

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++)
            sb.append(map.getOrDefault(Integer.parseInt(tokens.nextToken()), 0)).append(" ");

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end