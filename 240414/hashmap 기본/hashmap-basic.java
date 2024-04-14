import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static final Map<Integer, Integer> map = new HashMap<>();

    private static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            String cmd = tokens.nextToken();

            if(cmd.equals("add")) {
                int key = Integer.parseInt(tokens.nextToken());
                int value = Integer.parseInt(tokens.nextToken());

                map.put(key, value);
            } else if(cmd.equals("find")) {
                int key = Integer.parseInt(tokens.nextToken());
                int value = map.getOrDefault(key, -1);

                if(value == -1)
                    sb.append("None\n");
                else
                    sb.append(value).append("\n");
            } else {
                int key = Integer.parseInt(tokens.nextToken());
                map.remove(key);
            }
        }

        System.out.print(sb);
    }
}