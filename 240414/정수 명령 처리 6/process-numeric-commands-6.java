import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static final PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static int n;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            tokens = new StringTokenizer(br.readLine());

            String cmd = tokens.nextToken();

            if(cmd.equals("push")) {
                int value = Integer.parseInt(tokens.nextToken());

                pq.offer(-value);
            } else if(cmd.equals("pop")) {
                int value = -pq.poll();

                sb.append(value).append("\n");
            } else if(cmd.equals("size")) {
                sb.append(pq.size()).append("\n");
            } else if(cmd.equals("empty")) {
                int isEmpty = pq.isEmpty() ? 1 : 0;

                sb.append(isEmpty).append("\n");
            } else if(cmd.equals("top")) {
                sb.append(-pq.peek()).append("\n");
            }
        }

        System.out.print(sb);
    }
}