import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static final Set<Integer> set = new HashSet<>();

    private static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            String cmd = tokens.nextToken();

            if(cmd.equals("add")) {
                int key = Integer.parseInt(tokens.nextToken());

                set.add(key);
            } else if(cmd.equals("find")) {
                int key = Integer.parseInt(tokens.nextToken());

                sb.append(set.contains(key)).append("\n");
            } else {
                int key = Integer.parseInt(tokens.nextToken());
                set.remove(key);
            }
        }

        System.out.print(sb);
    }
}