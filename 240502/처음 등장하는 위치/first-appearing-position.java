import java.util.*;
import java.util.Map.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;
    private static final StringBuilder sb = new StringBuilder();
    
    private static int n = 0;   //  숫자 개수
    private static final Map<Integer, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        tokens = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(tokens.nextToken());
            
            if(!map.containsKey(num))
                map.put(num, i + 1);
        }

        for(Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int pos = entry.getValue();
            sb.append(num).append(" ").append(pos).append("\n");
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end