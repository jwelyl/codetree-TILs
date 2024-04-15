import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;
    
    private static final TreeSet<Integer> treeSet = new TreeSet<>();
    private static final HashMap<Integer, Integer> hashMap = new HashMap<>();

    private static int n, q;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());
    
        n = Integer.parseInt(tokens.nextToken());
        q = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            treeSet.add(Integer.parseInt(tokens.nextToken()));

        int idx = 0;
        for(int num : treeSet)
            hashMap.put(num, idx++);

        for(int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokens.nextToken());
            int to = Integer.parseInt(tokens.nextToken());

            sb.append(hashMap.get(to) - hashMap.get(from) + 1).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end