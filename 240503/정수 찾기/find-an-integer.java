import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;
    private static int m;

    private static final Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            set.add(Integer.parseInt(tokens.nextToken()));
        
        m = Integer.parseInt(br.readLine());
        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i< m; i++) {
            if(set.contains(Integer.parseInt(tokens.nextToken())))
                sb.append("1\n");
            else
                sb.append("0\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end