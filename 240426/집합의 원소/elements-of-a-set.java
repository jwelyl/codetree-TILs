import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final StringBuilder sb = new StringBuilder();

    private static int n;
    private static int m;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        parents = new int[n + 1];
        for(int i = 0; i <= n; i++)
            parents[i] = i;

        for(int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int opt = Integer.parseInt(tokens.nextToken());
            int v1 = Integer.parseInt(tokens.nextToken());
            int v2 = Integer.parseInt(tokens.nextToken());

            v1 = findDS(v1);
            v2 = findDS(v2);

            if(opt == 1)
                sb.append((v1 == v2) ? 1 : 0).append("\n");
            else
                union(v1, v2);
        }

        System.out.print(sb);
    }   //  main-end

    private static int findDS(int v) {
        if(v == parents[v])
            return v;

        return parents[v] = findDS(parents[v]);
    }

    private static void union(int v1, int v2) {
        if(v1 != v2)
            parents[v2] = v1;
    }
}   //  Main-class-end