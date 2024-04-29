import java.util.*;
import java.io.*;
import java.util.Map.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer tokens;

    private static int n;   //  원소 개수
    private static int k;   //  자주 등장한 k개의 수

    private static final Map<Integer, Integer> map = new HashMap<>();
    
    private static final PriorityQueue<Num> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        n = Integer.parseInt(tokens.nextToken());
        k = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokens.nextToken());

            map.put(num, map.getOrDefault(num, 0) + 1);        
        }

        Set<Entry<Integer, Integer>> entrySet = map.entrySet();

        for(Entry<Integer, Integer> entry : entrySet)
            pq.offer(new Num(entry.getKey(), entry.getValue()));

        while(k-- > 0) {
            Num num = pq.poll();
            sb.append(num.num).append(" ");
        }

        System.out.println(sb);
    }   //  main-end

    private static class Num implements Comparable<Num> {
        int cnt;    //  등장 횟수
        int num;    //  등장 횟수

        public Num(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Num num) {
            if(this.cnt == num.cnt)
                return Integer.compare(num.num, this.num);

            return Integer.compare(num.cnt, this.cnt);
        }
    }
}   //  Main-class-end