import java.util.*;
import java.util.Map.*;
import java.io.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final Map<Character, Integer> map = new LinkedHashMap<>();
    private static char ans = 0;
    private static String input = "";

    public static void main(String[] args) throws IOException {
        input = br.readLine();

        for(int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
        
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        Set<Entry<Character, Integer>> entrySet = map.entrySet();

        for(Entry<Character, Integer> entry : entrySet) {
            char ch = entry.getKey();
            int cnt = entry.getValue();

            if(cnt == 1) {
                ans = ch;
                break;
            }
        }

        System.out.println(ans == 0 ? "None" : ans);
    }
}