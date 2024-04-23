import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;

    private static int n;   //  수 개수

    private static final PriorityQueue<Integer> pq = new PriorityQueue<>();

    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++)
            pq.offer(Integer.parseInt(tokens.nextToken()));

        while(pq.size() >= 2) {
            int num1 = pq.poll();
            int num2 = pq.poll();

            ans += (num1 + num2);
            pq.offer(num1 + num2);
        }

        System.out.println(ans);
    }   //  main-end
}   //  Main-class-end