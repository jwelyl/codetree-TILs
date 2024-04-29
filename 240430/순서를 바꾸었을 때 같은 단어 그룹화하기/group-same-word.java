import java.util.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final Map<String, Integer> map = new HashMap<>();

    private static int n;           //  단어 개수
    private static int maxCnt = 0;  //  가장 많이 등장한 그룹 단어 개수
    
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            char[] input = br.readLine().toCharArray();
            Arrays.sort(input);
            String str = new String(input);

            map.put(str, map.getOrDefault(str, 0) + 1);

            maxCnt = Math.max(maxCnt, map.get(str));
        }

        System.out.println(maxCnt);
    }   //  main-end
}   //  Main-class-end