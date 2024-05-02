import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;
    private static int k;

    private static int[] a;
    private static int[] b;

    private static int[] pos;

    private static Set<Integer>[] sets;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());
        
        a = new int[k];
        b = new int[k];
        pos = new int[n + 1];

        sets = new Set[n + 1];
        for(int i = 1; i <= n; i++)
            sets[i] = new HashSet<>();

        for(int i = 1; i <= n; i++) {
            pos[i] = i;
            sets[i].add(pos[i]);
        }

        for(int i = 0; i < k; i++) {
            tokens = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(tokens.nextToken());
            b[i] = Integer.parseInt(tokens.nextToken());
        }

        for(int i = 0; i < 3 * k; i++) {
            int aIdx = a[i % k];
            int bIdx = b[i % k];

            int aNum = pos[aIdx];
            int bNum = pos[bIdx];

            sets[aNum].add(bIdx);
            sets[bNum].add(aIdx);

            pos[bIdx] = aNum;
            pos[aIdx] = bNum;
        }

        for(int i = 1; i <= n; i++)
            sb.append(sets[i].size()).append("\n");
    
        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end