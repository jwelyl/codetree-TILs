import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX = 1_000_002;

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  점의 개수
    private static int q;   //  범위 개수

    private static final boolean[] pos = new boolean[MAX];  //  pos[i] : 위치에 점이 있을 경우 true
    private static final int[] prefixCnt = new int[MAX];  //  prefixCnt[i] : 위치 1부터 i까지 누적 점의 개수

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        q = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++)
            pos[Integer.parseInt(tokens.nextToken()) + 1] = true;

        for(int i = 1; i < MAX; i++)
            prefixCnt[i] = prefixCnt[i - 1] + (pos[i] ? 1 : 0);

        for(int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(tokens.nextToken()) + 1;
            int to = Integer.parseInt(tokens.nextToken()) + 1;

            sb.append(prefixCnt[to] - prefixCnt[from - 1]).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end