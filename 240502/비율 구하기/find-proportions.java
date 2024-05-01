import java.util.*;
import java.util.Map.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder sb = new StringBuilder();
    
    private static int n = 0;   //  단어 개수
    private static final Map<String, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            String word = br.readLine();

            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for(Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int cnt = entry.getValue();
            double portion = 100.0 * cnt / n;

            sb.append(String.format("%s %.4f\n", word, portion));
        }

        System.out.print(sb);
    }   //  main-end
}   //  Main-class-end